package shadercat.automato;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    ImageView logo;
    EditText email;
    EditText password;
    TextView createAccount;

    private int clickCounter = 0;

    private Timer clickTimer;
    private MyTimerTask clickTimerTask;

    Animation logo_anim1;
    Animation logo_anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo_loginActivity);
        login_btn = findViewById(R.id.btn_login);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        createAccount = findViewById(R.id.link_signup);
        final Context context = this;

        logo_anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate_logo);
        logo_anim2 = AnimationUtils.loadAnimation(this, R.anim.shake_logo);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params
                        = RequestParamsFactory.getLogin(email.getText().toString().trim(), password.getText().toString());

                logo.startAnimation(logo_anim1);
                HttpClient.post(ConstantValues.AuthURL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if (ResponseHandler.IsSuccessed(response)) {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            logo.startAnimation(logo_anim2);
                            ThematicSnackbar.SnackbarTextShow(getString(R.string.wrongLogData), email);
                        }
                    }
                });
            }
        });


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (clickCounter >= 1) {
            finishAffinity();
        } else {
            ThematicSnackbar.SnackbarTextShow(getString(R.string.clickToExit), email);
            clickCounter++;
            clickTimer = new Timer();
            clickTimerTask = new MyTimerTask(new Action() {
                @Override
                public void action() {
                    clickCounter = 0;
                }
            });
            clickTimer.schedule(clickTimerTask, 3000);
        }
    }

    class MyTimerTask extends TimerTask {
        Action action;

        MyTimerTask(Action act) {
            action = act;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    action.action();
                }
            });
        }
    }

    interface Action {
        void action();
    }
}
