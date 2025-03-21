package de.androidcrypto.android_hce_beginner_app.JWT.Providers;

import de.androidcrypto.android_hce_beginner_app.JWT.Service.AuthService;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.RefreshTokenService;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.UserService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static final String BASE_URL = "https://your-api-url.com/"; // Reemplaza con la URL base de tu API

    // Proveer Retrofit para UserService (con autenticación)
    public static UserService provideAuthenticationApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(UserService.class);
    }

    // Proveer Retrofit para RefreshTokenService
    public static RefreshTokenService provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(RefreshTokenService.class);
    }

    // Proveer Retrofit para AuthService (sin autenticación)
    public static AuthService provideAuthenticationApiWithoutAuth(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(AuthService.class);
    }
}
