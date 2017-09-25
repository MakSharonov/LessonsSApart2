package com.vl.mak.servicebackbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

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
        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK, 0);

        es.execute(new MyRun(time, task));

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyRun implements Runnable {

        int time;
        int taskId;

        public MyRun(int time, int taskId) {
            super();
            this.time = time;
            this.taskId = taskId;
        }

        @Override
        public void run() {

            try {
                Intent intent = new Intent(MainActivity.BROADCAST_ACTION);

                intent.putExtra(MainActivity.PARAM_TASK, taskId);
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_START);
                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(time);

                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_FINISH);
                intent.putExtra(MainActivity.PARAM_RESULT, time*100);
                sendBroadcast(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
