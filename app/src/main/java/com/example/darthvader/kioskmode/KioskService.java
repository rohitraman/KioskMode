package com.example.darthvader.kioskmode;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;


public class KioskService extends Service {

    boolean running = false;
    Context context;

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        context = this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handleKioskMode();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    stopSelf();
                }while (running);

            }
        }).start();
        return Service.START_NOT_STICKY;
    }

    private void handleKioskMode() {
        // is Kiosk Mode active?
        if (SetKioskModeClass.isKioskMode(context)) {
            // is App in background?
            if (isInBackground()) {
                restoreApp(); // restore!
            }
        }
    }

    private boolean isInBackground() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        Log.i("Hello",taskInfo.toString());
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return (!context.getApplicationContext().getPackageName().equals(componentInfo.getPackageName()));
    }

    private void restoreApp() {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
