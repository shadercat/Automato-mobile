package shadercat.automato;

import com.loopj.android.http.RequestParams;

public class RequestParamsFactory {
    public static RequestParams getLogin(String email, String password) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        return params;
    }
}
