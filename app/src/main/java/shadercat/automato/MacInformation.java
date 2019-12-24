package shadercat.automato;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MacInformation extends AppCompatActivity {
    public static final String MAC_ID_EXTRA = "mac_id";

    TextView mac_name;
    TextView mac_id;
    TextView state;
    TextView prod_sate;
    ImageView backArrow;
    Button resolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_information);
        mac_name = findViewById(R.id.mac_name_info);
        mac_id = findViewById(R.id.mac_id_info);
        state = findViewById(R.id.state_info);
        prod_sate = findViewById(R.id.prod_state_info);
        backArrow = findViewById(R.id.back_arrow_info);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        resolve = findViewById(R.id.resolve_info);
        resolve.setEnabled(false);
        resolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient.post(ConstantValues.resLogs, RequestParamsFactory.resolveLog(mac_id.getText().toString()), new JsonHttpResponseHandler() {

                });
                ThematicSnackbar.SnackbarTextShow(getString(R.string.sendReq), mac_name);
            }
        });
        backArrow.setImageResource(R.drawable.ic_filter_tilt_shift_black_24dp);

        final Animation logo_anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate_logo);
        final Animation logo_anim2 = AnimationUtils.loadAnimation(this, R.anim.shake_logo);
        backArrow.startAnimation(logo_anim1);

        String macId = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            macId = (String) arguments.getSerializable(MAC_ID_EXTRA);
        }
        HttpClient.get(ConstantValues.getMachineInfo, RequestParamsFactory.getMachineInfo(macId), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    final Machine mac = ResponseHandler.GetMachine(response);
                    mac_name.setText(mac.getName());
                    mac_id.setText(getString(R.string.placeholderMacId, mac.getMacId()));
                    String stat = mac.getState();
                    state.setText(stat);
                    state.setBackgroundColor(getColor(stat.equals("online") ? R.color.colorDarkGreen : R.color.colorGray));
                    String prodSt = mac.getProdState();
                    prod_sate.setText(prodSt);
                    prod_sate.setBackgroundColor(getColor(prodSt.equals("normal") ? R.color.colorDarkGreen : R.color.colorGray));
                    backArrow.setImageResource(R.drawable.ic_arrow_back_black_24dp);
                    backArrow.startAnimation(logo_anim2);
                    resolve.setEnabled(true);

                }
            }
        });
    }
}
