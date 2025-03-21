package de.androidcrypto.android_hce_beginner_app;

import static android.content.Context.VIBRATOR_SERVICE;

import android.app.Activity;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class Utils {

    private static String secretKey = "7f4a83c8b4d9d3e2f9e3db943cd98b8adf07d8e0b60f9a62f3655d6e0a6bcae0d317bc1e7bfb3e9885e0f574c4b7c85f8f0d3b3ff6fc6f6dfac9bf68849f550";
    public static String bytesToHexNpe(byte[] bytes) {
        if (bytes == null) return "";
        StringBuffer result = new StringBuffer();
        for (byte b : bytes)
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void doVibrate(Activity activity) {
        if (activity != null) {
            // Make a Sound
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ((Vibrator) activity.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(50, 10));
            } else {
                Vibrator v = (Vibrator) activity.getSystemService(VIBRATOR_SERVICE);
                v.vibrate(50);
            }
        }
    }

    public static String generateJwt() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("SYONOCEAN")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600)) // Expira en un dia
                    .withClaim("UsuarioId", 123)
                    .withClaim("EmpresaId", 1)
                    .withAudience("http://foo.com")
                    .sign(algorithm); // Firmamos el JWT con la clave secreta

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
