package de.androidcrypto.android_hce_beginner_app.JWT.Service;

import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.AuthNetworkResponse;
import retrofit2.Response;
import retrofit2.http.POST;

public interface RefreshTokenService {

    @POST("v3/auth/refresh-token")
    Response<AuthNetworkResponse> refreshToken();
}

