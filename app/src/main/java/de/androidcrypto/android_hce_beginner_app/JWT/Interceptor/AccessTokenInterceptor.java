package de.androidcrypto.android_hce_beginner_app.JWT.Interceptor;

import java.io.IOException;

import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor {

    private final JwtTokenManager tokenManager;

    public AccessTokenInterceptor(JwtTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public String intercept() throws IOException {
        return tokenManager.getAccessJwt();
    }
}
