package shadercat.automato;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EmptyActivity extends AppCompatActivity {

    ImageView logo;
    Animation logo_anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        logo = findViewById(R.id.logo_splash);
        logo_anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate_logo);


        logo.startAnimation(logo_anim1);
        PreferencesStorage.InitializeStorage(this);
        PersistentCookieStore store = new PersistentCookieStore(this);
        HttpClient.setCookieStorage(store);

        final Intent login = new Intent(this, LoginActivity.class);
        final Intent main = new Intent(this, MainActivity.class);


        HttpClient.get(ConstantValues.isAuthURL, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    startActivity(main);
                } else {
                    startActivity(login);
                }
                finish();
            }
        });


    }
}
