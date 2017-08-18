package com.vl.mak.handler;

import android.app.Activity;
import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                tvInfo.setText("Files downloaded: " + msg.what);
                if (msg.what==10) btnStart.setEnabled(true);
            }
        };
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                btnStart.setEnabled(false);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=1; i<=10; i++) {
                            downloadFile();
                            h.sendEmptyMessage(i);
                            Log.d(LOG_TAG, "Files downloaded: " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "Test");
                break;
            default:
                break;
        }
    }

    public void bad_thread_onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=1; i<=10; i++) {
                            downloadFile();
                            tvInfo.setText("Files downloaded: " + i);
                            Log.d(LOG_TAG, "Files downloaded: " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "Test");
                break;
            default:
                break;
        }
    }

    public void bad_onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                for (int i=1; i<=10; i++) {
                    downloadFile();
                    tvInfo.setText("Files downloaded: " + i);
                    Log.d(LOG_TAG, "Files downloaded: " + i);
                }
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "Test");
                break;
            default:
                break;
        }
    }

    public void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
