package com.vl.mak.servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    final static String LOG_TAG = "myLogs";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Service onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "Sevice binded");
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG_TAG, "Service rebinded");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "Service unbinded");
        return true; // if false you will never see onRebind, silent rebinding
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Service destroyed");
    }
}
