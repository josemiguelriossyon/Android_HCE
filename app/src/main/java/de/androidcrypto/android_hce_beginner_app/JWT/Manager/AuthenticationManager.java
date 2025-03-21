package de.androidcrypto.android_hce_beginner_app.JWT.Manager;

import de.androidcrypto.android_hce_beginner_app.JWT.Model.Request.LoginRequest;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.AuthNetworkResponse;
import de.androidcrypto.android_hce_beginner_app.JWT.Providers.RetrofitProvider;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.UserNetworkResponse;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.ResultOf;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.AuthService;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.RefreshTokenService;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.UserService;
import okhttp3.OkHttpClient;
import retrofit2.Response;

public class AuthenticationManager {

    private final JwtTokenManager jwtTokenManager;
    private final AuthService authService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationManager(JwtTokenManager jwtTokenManager, OkHttpClient authenticatedClient, OkHttpClient refreshClient, OkHttpClient publicClient) {
        this.jwtTokenManager = jwtTokenManager;

        this.userService = RetrofitProvider.provideAuthenticationApi(publicClient);
        this.authService = RetrofitProvider.provideAuthenticationApiWithoutAuth(authenticatedClient);
        this.refreshTokenService = RetrofitProvider.provideRetrofit(refreshClient);
    }

    public ResultOf<AuthNetworkResponse> login(LoginRequest loginRequest) {
        Response<AuthNetworkResponse> response = authService.login(loginRequest);
        if (response.isSuccessful() && response.body() != null) {
            AuthNetworkResponse authResponse = response.body();
            jwtTokenManager.saveAccessJwt(authResponse.getAccessJwt());
            jwtTokenManager.saveRefreshJwt(authResponse.getRefreshJwt());
            return ResultOf.success(response.body());
        }
        return ResultOf.failure(new Exception("Login failed"));
    }

    public ResultOf<UserNetworkResponse> fetchUser(long officeId, long userId) {
        Response<UserNetworkResponse> response = userService.fetchUser(officeId, userId);
        if (response.isSuccessful() && response.body() != null) {
            return ResultOf.success(response.body());
        }
        return ResultOf.failure(new Exception("Failed to fetch user"));
    }
}
