package com.vl.mak.servicebindclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final static String LOG_TAG = "myLogs";

    Intent intent;
    ServiceConnection serviceConnection;
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.vl.mak.servicebindserver.MyService");
        intent.setPackage("com.vl.mak.servicebindserver");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                bound = true;
                Log.d(LOG_TAG, "Main activity: service connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                bound = false;
                Log.d(LOG_TAG, "Main activity: service disconnected");
            }
        };
    }

    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickStop(View v) {
        stopService(intent);
    }

    public void onClickBind(View v) {
        bindService(intent, serviceConnection, 0);//BIND_AUTO_CREATE);
    }

    public void onClickUnbind(View v) {
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnbind(null);
    }
}
