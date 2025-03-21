package de.androidcrypto.android_hce_beginner_app.JWT.Model.Response;

import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenManager;

public class AuthNetworkResponse implements JwtTokenManager {

    private String accessToken;
    private String refreshToken;

    // Constructor
    public AuthNetworkResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getters y Setters
    public String getAccessJwt() {
        return accessToken;
    }

    public void saveAccessJwt(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshJwt() {
        return refreshToken;
    }

    public void saveRefreshJwt(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public void clearAllTokens() {

    }
}