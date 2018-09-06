package com.example.darthvader.kioskmode;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP));
    private Button btnHidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHidden = (Button) findViewById(R.id.btn_exitkiosk);
        SetKioskModeClass.setKioskMode(getApplicationContext(), true);
        btnHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetKioskModeClass.setKioskMode(getApplicationContext(), false);
                Toast.makeText(getApplicationContext(), "You can exit the app now", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityManager manager = (ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        manager.moveTaskToFront(getTaskId(),0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager manager = (ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        manager.moveTaskToFront(getTaskId(),0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager manager = (ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        manager.moveTaskToFront(getTaskId(),0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(intent);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else
            return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            System.exit(0);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }


}
