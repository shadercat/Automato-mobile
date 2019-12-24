package shadercat.automato;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ActionFragment extends Fragment implements View.OnClickListener {

    LinearLayout addMac;
    LinearLayout delMac;
    LinearLayout bindMac;
    LinearLayout unbindMac;
    LinearLayout delLog;

    private OnFragmentInteractionListener mListener;

    public ActionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);
        addMac = view.findViewById(R.id.add_mac_action);
        delMac = view.findViewById(R.id.delete_mac_action);
        bindMac = view.findViewById(R.id.bind_mac_action);
        unbindMac = view.findViewById(R.id.unbind_mac_action);
        delLog = view.findViewById(R.id.del_log_action);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addMac.setOnClickListener(this);
        delMac.setOnClickListener(this);
        bindMac.setOnClickListener(this);
        unbindMac.setOnClickListener(this);
        delLog.setOnClickListener(this);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_mac_action:
                AddDialog();
                return;
            case R.id.delete_mac_action:
                DelDialog();
                return;
            case R.id.bind_mac_action:
                BindDialog();
                return;
            case R.id.unbind_mac_action:
                UnbindDialog();
                return;
            case R.id.del_log_action:
                DelLogDialog();
                return;
        }
    }

    public interface OnFragmentInteractionListener {
        void onActionFragmentInteraction(Uri uri);
    }

    private void DelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.delMac));
        View inflater = this.getLayoutInflater().inflate(R.layout.dialog_del_alert, null);
        final EditText macId = inflater.findViewById(R.id.getMacId);
        final EditText codeMac = inflater.findViewById(R.id.getCode);
        builder.setView(inflater)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = macId.getText().toString().trim();
                        final String code = codeMac.getText().toString().trim();
                        HttpClient.delete(ConstantValues.delMac, RequestParamsFactory.standardMachine(name, code), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                if (ResponseHandler.IsSuccessed(response)) {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opSuccess), addMac);
                                } else {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opFail), addMac);
                                }
                            }
                        });
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void AddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.addMac));
        View inflater = this.getLayoutInflater().inflate(R.layout.dialog_add_alert, null);
        final EditText macId = inflater.findViewById(R.id.getMacId);
        final EditText codeMac = inflater.findViewById(R.id.getCode);
        final EditText name = inflater.findViewById(R.id.getName);
        builder.setView(inflater)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String mac_id = macId.getText().toString().trim();
                        final String code = codeMac.getText().toString().trim();
                        final String mac_name = name.getText().toString().trim();
                        HttpClient.put(ConstantValues.newMac, RequestParamsFactory.newMachine(mac_id, code, mac_name), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                if (ResponseHandler.IsSuccessed(response)) {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opSuccess), addMac);
                                } else {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opFail), addMac);
                                }
                            }
                        });
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void BindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.bindMac));
        View inflater = this.getLayoutInflater().inflate(R.layout.dialog_del_alert, null);
        final EditText macId = inflater.findViewById(R.id.getMacId);
        final EditText codeMac = inflater.findViewById(R.id.getCode);
        builder.setView(inflater)
                .setPositiveButton(R.string.bind, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = macId.getText().toString().trim();
                        final String code = codeMac.getText().toString().trim();
                        HttpClient.post(ConstantValues.bindMac, RequestParamsFactory.standardMachine(name, code), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                if (ResponseHandler.IsSuccessed(response)) {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opSuccess), addMac);
                                } else {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opFail), addMac);
                                }
                            }
                        });
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void UnbindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.unbindMac));
        View inflater = this.getLayoutInflater().inflate(R.layout.dialog_del_alert, null);
        final EditText macId = inflater.findViewById(R.id.getMacId);
        final EditText codeMac = inflater.findViewById(R.id.getCode);
        builder.setView(inflater)
                .setPositiveButton(R.string.unbind, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = macId.getText().toString().trim();
                        final String code = codeMac.getText().toString().trim();
                        HttpClient.post(ConstantValues.unbindMac, RequestParamsFactory.standardMachine(name, code), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                if (ResponseHandler.IsSuccessed(response)) {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opSuccess), addMac);
                                } else {
                                    ThematicSnackbar.SnackbarTextShow(getString(R.string.opFail), addMac);
                                }
                            }
                        });
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void DelLogDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.delLogs));
        View inflater = this.getLayoutInflater().inflate(R.layout.dialog_del_alert2, null);
        final EditText macId = inflater.findViewById(R.id.getMacId2);
        builder.setView(inflater)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = macId.getText().toString().trim();
                        ThematicSnackbar.SnackbarTextShow(getString(R.string.sendReq), addMac);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
