package com.vl.mak.servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final static String LOG_TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask tTask;
    long interval;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        interval = 500;

        timer = new Timer();
        schedule();
    }

    void schedule() {
        if (tTask != null) tTask.cancel();
        if (interval>0) {
            tTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "running");
                }
            };
            timer.schedule(tTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        interval += gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval -= gap;
        if (interval<0) interval = 0;
        schedule();
        return interval;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}
