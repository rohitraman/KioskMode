package com.example.darthvader.kioskmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Log.d("Service Started", ",...........................");
            Intent pushIntent = new Intent(context, LocationService.class);
            context.startService(pushIntent);
        } catch (Exception e) {
            Log.e("ff", "Df");
        }
    }
}
