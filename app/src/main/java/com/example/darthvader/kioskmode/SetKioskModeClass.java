package com.example.darthvader.kioskmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SetKioskModeClass {
    public static SharedPreferences preferences;
    public static boolean isKioskMode(Context context)
    {
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("kiosk_mode",false);
    }

    public static void setKioskMode(Context context,boolean value)
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("kiosk_mode", value).commit();
    }
}
