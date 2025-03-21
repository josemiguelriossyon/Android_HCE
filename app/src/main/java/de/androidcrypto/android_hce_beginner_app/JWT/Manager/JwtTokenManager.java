package de.androidcrypto.android_hce_beginner_app.JWT.Manager;

public interface JwtTokenManager {
    void saveAccessJwt(String token);
    void saveRefreshJwt(String token);
    String getAccessJwt();
    String getRefreshJwt();
    void clearAllTokens();
}
