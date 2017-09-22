package com.vl.mak.servicebackpendingintent;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final static String LOG_TAG = "myLogs";

    ExecutorService es;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        es = Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PendingIntent pi = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);
        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        es.execute(new MyRun(startId, pi, time));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyRun implements Runnable {

        int startId;
        PendingIntent pi;
        int time;

        public MyRun(int startId, PendingIntent pi, int time) {
            super();
            this.pi = pi;
            this.startId = startId;
            this.time = time;
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "Run #" + startId + " started");
            try {
                pi.send(MainActivity.STATUS_START);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT, time*100);
                pi.send(MyService.this, MainActivity.STATUS_FINISH, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            stopSelfResult(startId);
        }
    }

}
