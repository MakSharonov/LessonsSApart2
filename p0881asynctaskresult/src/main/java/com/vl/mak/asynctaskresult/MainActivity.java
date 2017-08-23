package com.vl.mak.asynctaskresult;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    TextView tvInfo;
    MyTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.btnGet:
                showResult();
                break;
        }
    }

    private void showResult() {
        try {
            int res = -1;
            res = mt.get(1, TimeUnit.SECONDS);
            Log.d(LOG_TAG, "res " + res);
            Toast.makeText(this, Integer.toString(res), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            Log.d(LOG_TAG, "sorry, timeout");
            e.printStackTrace();
        }

    }

    class MyTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_TAG, "Begin");
            tvInfo.setText("Begin");
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tvInfo.setText("End. Result: " + integer);
            Log.d(LOG_TAG, "End. Result: " + integer);
        }
    }
}
