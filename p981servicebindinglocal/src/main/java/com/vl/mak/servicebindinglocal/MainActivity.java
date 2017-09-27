package com.vl.mak.servicebindinglocal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    Intent intent;
    ServiceConnection serviceConnection;
    boolean bound;
    TextView tvInterval;

    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInterval = findViewById(R.id.tvInterval);

        intent = new Intent(this, MyService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myService = ((MyService.MyBinder) iBinder).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }

    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickUp(View v) {
        if (bound)
            tvInterval.setText(String.valueOf(myService.upInterval(500)));
    }

    public void onClickDown(View v) {
         if (bound)
            tvInterval.setText(String.valueOf(myService.downInterval(500)));
    }
}
