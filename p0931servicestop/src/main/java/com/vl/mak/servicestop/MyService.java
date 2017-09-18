package com.vl.mak.servicestop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService es;
    Object someRes;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
        es = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        int time = intent.getIntExtra("time", 1);
        MyRun mr = new MyRun(time, startId);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyRun implements Runnable {

        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#" + startId + " created");
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " started, time=" + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG, "MyRun#" + startId + " someRes class=" + someRes.getClass());
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun#" + startId + " NullPointerException");
            }
            stopWithResult();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " ending, stopSelf(" + startId + ")");
            stopSelf(startId);
        }

        void stopWithResult() {
            Log.d(LOG_TAG, "MyRun#" + startId + " ending, stopSelfResult(" + startId + ")" + stopSelfResult(startId));
        }
    }
}
