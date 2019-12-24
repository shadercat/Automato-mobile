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

    public static RequestParams newMachine(String mac_id, String code, String name) {
        RequestParams params = new RequestParams();
        params.put("mac_id", mac_id);
        params.put("code", code);
        params.put("state", "offline");
        params.put("prod_state", "normal");
        params.put("name", name);
        return params;
    }

    public static RequestParams standardMachine(String mac_id, String code) {
        RequestParams params = new RequestParams();
        params.put("mac_id", mac_id);
        params.put("code", code);
        return params;
    }

    public static RequestParams resolveLog(String mac_id) {
        return new RequestParams("mac_id", mac_id);
    }
}
