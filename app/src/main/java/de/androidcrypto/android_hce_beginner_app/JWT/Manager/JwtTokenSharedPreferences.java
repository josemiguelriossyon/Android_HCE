package de.androidcrypto.android_hce_beginner_app.JWT.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class JwtTokenSharedPreferences implements JwtTokenManager{
    private static final String PREF_NAME = "auth_preferences";
    private static final String ACCESS_JWT_KEY = "access_jwt";
    private static final String REFRESH_JWT_KEY = "refresh_jwt";

    private final SharedPreferences sharedPreferences;

    public JwtTokenSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }@Override
    public void saveAccessJwt(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_JWT_KEY, token);
        editor.apply();
    }

    @Override
    public void saveRefreshJwt(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REFRESH_JWT_KEY, token);
        editor.apply();
    }

    @Override
    public String getAccessJwt() {
        return sharedPreferences.getString(ACCESS_JWT_KEY, null);
    }

    @Override
    public String getRefreshJwt() {
        return sharedPreferences.getString(REFRESH_JWT_KEY, null);
    }

    @Override
    public void clearAllTokens() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ACCESS_JWT_KEY);
        editor.remove(REFRESH_JWT_KEY);
        editor.apply();
    }
}
