package com.vl.mak.handlermessagemanage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    Handler h;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Log.d(LOG_TAG, "what = " + message.what);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler(hc);

        sendMessages();
    }

    void sendMessages() {

        h.sendEmptyMessageDelayed(1, 1000);
        h.sendEmptyMessageDelayed(2, 2000);
        h.sendEmptyMessageDelayed(3, 3000);
        h.sendEmptyMessageDelayed(2, 4000);
        h.sendEmptyMessageDelayed(4, 5000);
        h.sendEmptyMessageDelayed(5, 6000);
        h.sendEmptyMessageDelayed(2, 7000);
        h.removeMessages(2);

    }
}
