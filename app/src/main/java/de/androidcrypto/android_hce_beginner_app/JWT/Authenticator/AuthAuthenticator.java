package de.androidcrypto.android_hce_beginner_app.JWT.Authenticator;

import java.io.IOException;

import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenManager;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.AuthNetworkResponse;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.RefreshTokenService;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AuthAuthenticator implements Authenticator {

    private final JwtTokenManager tokenManager;
    private final RefreshTokenService refreshTokenService;

    public AuthAuthenticator(JwtTokenManager tokenManager, RefreshTokenService refreshTokenService) {
        this.tokenManager = tokenManager;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String currentToken = tokenManager.getAccessJwt();
        synchronized (this) {
            String updatedToken = tokenManager.getAccessJwt();
            String token = (currentToken != null && !currentToken.equals(updatedToken)) ?
                    updatedToken : refreshAccessToken();

            if (token != null) {
                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .build();
            }
        }
        return null;
    }

    private String refreshAccessToken() throws IOException {
        retrofit2.Response<AuthNetworkResponse> newSessionResponse = refreshTokenService.refreshToken();
        if (newSessionResponse.isSuccessful() && newSessionResponse.body() != null) {
            String newAccessToken = newSessionResponse.body().getAccessJwt();
            String newRefreshToken = newSessionResponse.body().getRefreshJwt();

            tokenManager.saveAccessJwt(newAccessToken);
            tokenManager.saveRefreshJwt(newRefreshToken);
            return newAccessToken;
        }
        return null;
    }
}
