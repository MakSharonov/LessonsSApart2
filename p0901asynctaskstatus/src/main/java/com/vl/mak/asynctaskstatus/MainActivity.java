package com.vl.mak.asynctaskstatus;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    TextView tvInfo;
    MyTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);
    }

    void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startTask();
                break;
            case R.id.btnStatus:
                showStatus();
                break;
        }
    }

    void startTask() {
        mt = new MyTask();
        mt.execute();
        mt.cancel(false);
    }

    void showStatus() {
        if (mt != null) {
            if (mt.isCancelled())
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Execute");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i=0; i<5; i++) {
                    if (isCancelled()) return null;
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvInfo.setText("End");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            tvInfo.setText("Cancelled");
        }
    }
}
