package com.example.darthvader.kioskmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;



public class OnScreenOffReceiver extends BroadcastReceiver {
    AppContext appContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            appContext = (AppContext) context.getApplicationContext();
            if (SetKioskModeClass.isKioskMode(appContext)) {
                wakeUpDevice(appContext);
            }
        }
    }

    private void wakeUpDevice(AppContext context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock();

    }
}
