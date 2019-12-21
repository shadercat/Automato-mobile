package shadercat.automato;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {
    public static boolean IsSuccessed(JSONObject object) {
        boolean res = false;
        try {
            res = object.getBoolean("success");
        } catch (JSONException e) {
            Log.e("JSON", "Is failed: ", e);
        }
        return res;
    }


    public static List<Machine> GetMachines(JSONObject object) {
        List<Machine> machines = new ArrayList<>();
        try {
            JSONArray array = object.getJSONArray("data");
            JSONObject ob;
            for (int i = 0; i < array.length(); i++) {
                ob = array.getJSONObject(i);
                String name = ob.getString("name");
                String macId = ob.getString("mac_id");
                String prodState = ob.getString("prod_state");
                String id = ob.getString("_id");
                String state = ob.getString("state");
                machines.add(new Machine(name, macId, prodState, state, id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return machines;
    }

    public static Machine GetMachine(JSONObject object) {
        Machine mac = new Machine();
        try {
            JSONObject ob = object.getJSONObject("data");
            mac.setId(ob.getString("_id"));
            mac.setName(ob.getString("name"));
            mac.setMacId(ob.getString("mac_id"));
            mac.setProdState(ob.getString("prod_state"));
            mac.setState(ob.getString("state"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mac;
    }
}
