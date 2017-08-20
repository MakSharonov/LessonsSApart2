package com.vl.mak.handleradvmessage;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0;
    final int STATUS_CONNECTING = 1;
    final int STATUS_CONNECTED = 2;
    final int DOWNLOAD_START = 3;
    final int DOWNLOAD_FILE = 4;
    final int DOWNLOAD_END = 5;
    final int DOWNLOAD_NONE = 6;

    Handler h;

    TextView tvStatus;
    Button btnConnect;
    ProgressBar pbDownload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        pbDownload = (ProgressBar) findViewById(R.id.pbDownload);

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        pbDownload.setVisibility(View.GONE);
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Connected");
                        break;
                    case DOWNLOAD_START:
                        tvStatus.setText("Started downloading of " + msg.arg1 + " files.");
                        pbDownload.setMax(msg.arg1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);
                        break;
                    case DOWNLOAD_FILE:
                        tvStatus.setText("Downloading. " + msg.arg2 + " files left");
                        pbDownload.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case DOWNLOAD_END:
                        tvStatus.setText("Download completed");
                        break;
                    case DOWNLOAD_NONE:
                        tvStatus.setText("No files");
                        break;
                }
            }
        };
        h.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View v) {
        Thread t = new Thread(new Runnable() {

            Message msg;
            byte[] file;
            Random rand = new Random();

            @Override
            public void run() {
                try {
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);
                    h.sendEmptyMessage(STATUS_CONNECTED);

                    int filesCount = rand.nextInt(5);

                    if (filesCount==0) {
                        h.sendEmptyMessage(DOWNLOAD_NONE);
                        TimeUnit.MILLISECONDS.sleep(1500);
                        h.sendEmptyMessage(STATUS_NONE);
                    }

                    msg = h.obtainMessage(DOWNLOAD_START, filesCount, 0);
                    h.sendMessage(msg);

                    for(int i=1; i<=filesCount; i++) {
                        file = downloadFile();

                        msg = h.obtainMessage(DOWNLOAD_FILE, i, filesCount-i, file);
                        h.sendMessage(msg);
                    }

                    h.sendEmptyMessage(DOWNLOAD_END);

                    TimeUnit.MILLISECONDS.sleep(1500);

                    h.sendEmptyMessage(STATUS_NONE);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file) {

    }


}
