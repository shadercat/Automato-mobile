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

    public static List<Company> GetCompanies(JSONObject object) {
        List<Company> companies = new ArrayList<>();
        try {
            JSONObject ob = object.getJSONObject("data");
            JSONArray array = ob.getJSONArray("comp");
            JSONObject item;
            for (int i = 0; i < array.length(); i++) {
                item = array.getJSONObject(i);
                String name = item.getString("name");
                String email = item.getString("email");
                String compDescry = item.getString("comp_description");
                String createDate = item.getString("create_time");
                JSONObject add = item.getJSONObject("addData");
                String number = add.getString("number");
                String location = add.getString("location");
                companies.add(new Company(name, email, compDescry, number, location, createDate));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
