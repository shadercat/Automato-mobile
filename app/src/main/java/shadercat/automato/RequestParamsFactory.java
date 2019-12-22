package shadercat.automato;

import com.loopj.android.http.RequestParams;

public class RequestParamsFactory {
    public static RequestParams getLogin(String email, String password) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        return params;
    }

    public static RequestParams getMachineInfo(String mac_id) {
        return new RequestParams("mac_id", mac_id);
    }

    public static RequestParams getCompanyInfo(String email) {
        return new RequestParams("email", email);
    }
}
