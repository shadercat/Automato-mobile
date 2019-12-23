package shadercat.automato;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;


public class AccountFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;
    TextView name;
    TextView email;
    TextView type;
    TextView regDate;
    TextView phone;
    TextView location;
    TextView descry;
    Button logout;


    public AccountFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        name = view.findViewById(R.id.name_acc);
        email = view.findViewById(R.id.email_acc);
        type = view.findViewById(R.id.type_acc);
        regDate = view.findViewById(R.id.reg_date_acc);
        phone = view.findViewById(R.id.phone_acc);
        location = view.findViewById(R.id.location_acc);
        descry = view.findViewById(R.id.descry_acc);
        logout = view.findViewById(R.id.btn_logout);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HttpClient.get(ConstantValues.getUserInfo, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Company comp = ResponseHandler.GetUserCompany(response);
                    name.setText(comp.getName());
                    email.setText(getString(R.string.ph_email, comp.getEmail()));
                    type.setText(getString(R.string.ph_type, comp.getType()));
                    phone.setText(getString(R.string.ph_phoneNum, comp.getNumber()));
                    location.setText(getString(R.string.ph_location, comp.getLocation()));
                    descry.setText(getString(R.string.ph_descry, comp.getCompDescry()));
                    try {
                        SimpleDateFormat mongodt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                        mongodt.setTimeZone(TimeZone.getTimeZone("UTC"));
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        regDate.setText(getString(R.string.ph_regDate, date.format(mongodt.parse(comp.getCreateTime()))));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient.post(ConstantValues.logout, null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if (ResponseHandler.IsSuccessed(response)) {
                            Intent login = new Intent(getContext(), EmptyActivity.class);
                            startActivity(login);
                            getActivity().finish();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onAccountFragmentInteraction(Uri uri);
    }
}
