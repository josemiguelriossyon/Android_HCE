package de.androidcrypto.android_hce_beginner_app.JWT.Interceptor;

import java.io.IOException;

import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RefreshTokenInterceptor implements Interceptor{
    private final JwtTokenManager tokenManager;

    public RefreshTokenInterceptor(JwtTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String token = tokenManager.getRefreshJwt();
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
