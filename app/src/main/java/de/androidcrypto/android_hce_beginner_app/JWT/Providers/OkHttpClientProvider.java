package de.androidcrypto.android_hce_beginner_app.JWT.Providers;

import java.util.concurrent.TimeUnit;

import de.androidcrypto.android_hce_beginner_app.JWT.Authenticator.AuthAuthenticator;
import de.androidcrypto.android_hce_beginner_app.JWT.Interceptor.AccessTokenInterceptor;
import de.androidcrypto.android_hce_beginner_app.JWT.Interceptor.RefreshTokenInterceptor;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientProvider {

    public static OkHttpClient provideAccessOkHttpClient(
            AccessTokenInterceptor accessTokenInterceptor,
            AuthAuthenticator authAuthenticator) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .authenticator(authAuthenticator)  // Añadir el autenticador
                //.addInterceptor(accessTokenInterceptor)  // Añadir interceptor para el token de acceso
                .addInterceptor(loggingInterceptor)  // Añadir el interceptor de logs
                .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la conexión
                .readTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la lectura
                .writeTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la escritura
                .build();
    }

    // Proveer OkHttpClient para refresco de token
    public static OkHttpClient provideRefreshOkHttpClient(
            RefreshTokenInterceptor refreshTokenInterceptor) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)  // Añadir el interceptor de logs
                .addInterceptor(refreshTokenInterceptor)  // Añadir interceptor para el refresco de token
                .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la conexión
                .readTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la lectura
                .writeTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la escritura
                .build();
    }

    // Proveer OkHttpClient sin autenticación
    public static OkHttpClient provideUnauthenticatedOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)  // Añadir el interceptor de logs
                .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la conexión
                .readTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la lectura
                .writeTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera para la escritura
                .build();
    }
}
