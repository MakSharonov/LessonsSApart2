package com.vl.mak.asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    TextView tv;
    final static String LOG_TAG = "myLogs";
    MyTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Create activity " + this.hashCode());

        tv = findViewById(R.id.tv);

        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);
        Log.d(LOG_TAG, "Create MyTask " + mt.hashCode());
    }

    static class MyTask extends AsyncTask<Void, Integer, Void> {

        MainActivity activity;

        void link(MainActivity act) {
            activity = act;
        }

        void unlink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i=1; i<=10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    Log.d(MainActivity.LOG_TAG, "loop i=" + i + " activity=" + activity.hashCode() + " MyTask=" + this.hashCode());
                    publishProgress(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.tv.setText("Progress " + values[0]);
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        mt.unlink();
        return mt;
    }
}
