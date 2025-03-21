package de.androidcrypto.android_hce_beginner_app.JWT.Service;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.Request.LoginRequest;
import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.AuthNetworkResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("v3/auth/authenticate")
    Response<AuthNetworkResponse> login(@Body LoginRequest body);
}
