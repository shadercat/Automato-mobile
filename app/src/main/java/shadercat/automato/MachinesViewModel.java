package shadercat.automato;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MachinesViewModel extends ViewModel {

    MutableLiveData<List<Machine>> machines;

    SnackbarMessage snackbar = new SnackbarMessage();

    public LiveData<List<Machine>> getData() {
        if (machines == null) {
            machines = new MutableLiveData<>();
            loadData();
        }
        return machines;
    }

    public SnackbarMessage getSnackbar() {
        return snackbar;
    }

    private void loadData() {
        Log.e("DEV", "load data");
        HttpClient.get(ConstantValues.getMachines, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    Log.e("DEV", "success getData");
//                    snackbar.setValue(R.string.downloadError);
                    machines.postValue(ResponseHandler.GetMachines(response));
                } else {
                    Log.e("DEV", "fail getData");
//                    snackbar.setValue(R.string.downloadError);
                }
            }
        });
    }

}
