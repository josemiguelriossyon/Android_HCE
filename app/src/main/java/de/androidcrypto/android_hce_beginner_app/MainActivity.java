package de.androidcrypto.android_hce_beginner_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import de.androidcrypto.android_hce_beginner_app.JWT.Authenticator.AuthAuthenticator;
import de.androidcrypto.android_hce_beginner_app.JWT.Interceptor.AccessTokenInterceptor;
import de.androidcrypto.android_hce_beginner_app.JWT.Interceptor.RefreshTokenInterceptor;
import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenManager;
import de.androidcrypto.android_hce_beginner_app.JWT.Manager.JwtTokenSharedPreferences;
import de.androidcrypto.android_hce_beginner_app.JWT.Providers.OkHttpClientProvider;
import de.androidcrypto.android_hce_beginner_app.JWT.Providers.RetrofitProvider;
import de.androidcrypto.android_hce_beginner_app.JWT.Service.RefreshTokenService;
import okhttp3.OkHttpClient;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient authenticatedClient;
    private OkHttpClient refreshClient;
    private OkHttpClient publicClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        JwtTokenManager jwtTokenManager = new JwtTokenSharedPreferences(getApplicationContext());

        String jwt = Utils.generateJwt();

        jwtTokenManager.saveAccessJwt(jwt);
        jwtTokenManager.saveRefreshJwt(jwt);

        // refreshTokenService = RetrofitProvider.provideRetrofit(refreshClient);

        AccessTokenInterceptor accessTokenInterceptor = new AccessTokenInterceptor(jwtTokenManager);
        //AuthAuthenticator authAuthenticator = new AuthAuthenticator(jwtTokenManager, refreshTokenService);
        RefreshTokenInterceptor refreshTokenInterceptor = new RefreshTokenInterceptor(jwtTokenManager);

        //authenticatedClient = OkHttpClientProvider.provideAccessOkHttpClient(accessTokenInterceptor, authAuthenticator);
        refreshClient = OkHttpClientProvider.provideRefreshOkHttpClient(refreshTokenInterceptor);
        publicClient = OkHttpClientProvider.provideUnauthenticatedOkHttpClient();

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setOnItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.read) {
            selectedFragment = new ReadFragment();
        } else if (itemId == R.id.write) {
            selectedFragment = new WriteFragment();
        }

        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };


}