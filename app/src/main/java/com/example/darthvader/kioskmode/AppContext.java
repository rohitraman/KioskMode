package com.example.darthvader.kioskmode;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/**
 * Created by darthvader on 5/9/18.
 */

public class AppContext extends Application {
    private AppContext instance;
    private PowerManager.WakeLock wakeLock;
    private OnScreenOffReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerKioskModeScreenOffListener();
        startKioskService();
    }

    private void registerKioskModeScreenOffListener() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        OnScreenOffReceiver receiver = new OnScreenOffReceiver();
        registerReceiver(receiver, filter);
    }

    public PowerManager.WakeLock getWakeLock() {
        if (wakeLock == null) {
            PowerManager manager = (PowerManager) getSystemService(POWER_SERVICE);
            wakeLock = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup");
        }
        return wakeLock;
    }

    public void startKioskService()
    {
        startService(new Intent(this,KioskService.class));
    }
}
