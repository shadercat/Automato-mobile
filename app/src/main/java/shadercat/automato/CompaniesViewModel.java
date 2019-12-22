package shadercat.automato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CompaniesViewModel extends ViewModel {
    MutableLiveData<List<Company>> companies;

    public LiveData<List<Company>> getData() {
        if (companies == null) {
            companies = new MutableLiveData<>();
            loadData();
        }
        return companies;
    }

    private void loadData() {
        HttpClient.get(ConstantValues.getCompanies, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (ResponseHandler.IsSuccessed(response)) {
                    companies.postValue(ResponseHandler.GetCompanies(response));
                }
            }
        });
    }
}
