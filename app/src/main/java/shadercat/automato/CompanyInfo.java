package shadercat.automato;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class CompanyInfo extends AppCompatActivity {

    public final static String COMP_EMAIL_EXTRA = "email";

    TextView compName;
    TextView compEmail;
    TextView number;
    TextView location;
    TextView regData;
    TextView addInfo;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        compName = findViewById(R.id.comp_name_info);
        compEmail = findViewById(R.id.comp_email_info);
        number = findViewById(R.id.phoneNumber_info);
        location = findViewById(R.id.location_info);
        regData = findViewById(R.id.registration_data);
        addInfo = findViewById(R.id.descry_info);
        backArrow = findViewById(R.id.back_arrow_info2);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        backArrow.setImageResource(R.drawable.ic_filter_tilt_shift_black_24dp);
        final Animation logo_anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate_logo);
        final Animation logo_anim2 = AnimationUtils.loadAnimation(this, R.anim.shake_logo);
        backArrow.startAnimation(logo_anim1);

        String email = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            email = (String) arguments.getSerializable(COMP_EMAIL_EXTRA);
        }
        HttpClient.get(ConstantValues.getCompanyInfo, RequestParamsFactory.getCompanyInfo(email), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Company comp = ResponseHandler.GetCompany(response);
                    compName.setText(comp.getName());
                    compEmail.setText(comp.getEmail());
                    number.setText(comp.getNumber());
                    location.setText(comp.getLocation());
                    addInfo.setText(comp.getCompDescry());
                    backArrow.setImageResource(R.drawable.ic_arrow_back_black_24dp);
                    backArrow.startAnimation(logo_anim2);
                    try {
                        SimpleDateFormat mongodt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                        mongodt.setTimeZone(TimeZone.getTimeZone("UTC"));
                        regData.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mongodt.parse(comp.getCreateTime())));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
