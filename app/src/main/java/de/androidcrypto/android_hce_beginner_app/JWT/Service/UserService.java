package de.androidcrypto.android_hce_beginner_app.JWT.Service;

import de.androidcrypto.android_hce_beginner_app.JWT.Model.Response.UserNetworkResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("office/{officeId}/users/{userId}")
    Response<UserNetworkResponse> fetchUser(
            @Path("officeId") long officeId,
            @Path("userId") long userId
    );
}
