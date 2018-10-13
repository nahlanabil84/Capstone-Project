package com.nanodegree.nahla.capstoneproject.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static final String USER_FB_ID = "USER_FB_ID";
    public static SharedPreferences sharedPreferences;
    private static SharedPref pref;
    public static SharedPreferences.Editor editor;

    public void clearAllData() {
        editor.clear();
        editor.commit();
    }

    public SharedPref(Context context) {
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public void putUserFBId(String uid) {
        editor.putString(USER_FB_ID, uid);
        editor.commit();
    }

    public String getUserFbId() {
        return sharedPreferences.getString(USER_FB_ID, "");
    }

}
