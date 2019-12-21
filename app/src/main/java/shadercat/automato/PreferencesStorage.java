package shadercat.automato;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesStorage {
    public static String email = "";
    public static String password = "";
    public static String cookie = "";
    public static boolean isAuth = false;

    public static void InitializeStorage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        PreferencesStorage.email = preferences.getString("email", "");
        PreferencesStorage.password = preferences.getString("pass", "");
        PreferencesStorage.cookie = preferences.getString("cookie", "");
        PreferencesStorage.isAuth = preferences.getBoolean("isAuth", false);
        if (!preferences.contains("email")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", "");
            editor.putString("pass", "");
            editor.putString("cookie", "");
            editor.putBoolean("isAuth", false);
            editor.apply();
        }
    }

    public static void ClearStorage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        InitializeStorage(context);
    }

    public static void SaveStorage(Context context, String email, String password, String cookie) {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.contains("email")) {
            editor.putString("email", "");
            editor.putString("pass", "");
            editor.putString("cookie", "");
            editor.putBoolean("isAuth", false);
            editor.apply();
        }
        editor.putString("email", email);
        editor.putString("pass", "");
        editor.putString("cookie", cookie);
        editor.putBoolean("isAuth", true);
    }
}
