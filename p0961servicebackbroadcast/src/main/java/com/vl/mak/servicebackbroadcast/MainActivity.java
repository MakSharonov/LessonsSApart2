package com.vl.mak.servicebackbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final static int TASK1_CODE = 1;
    final static int TASK2_CODE = 2;
    final static int TASK3_CODE = 3;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    TextView tvTask1, tvTask2, tvTask3;

    final static String BROADCAST_ACTION = "com.vl.mak.sevicebackbroadcast";

    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = findViewById(R.id.tvTask1);
        tvTask2 = findViewById(R.id.tvTask2);
        tvTask3 = findViewById(R.id.tvTask3);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int status = intent.getIntExtra(PARAM_STATUS,0);
                int requestCode = intent.getIntExtra(PARAM_TASK,0);
                int result = intent.getIntExtra(PARAM_RESULT, 0);

                if (status == STATUS_START) {
                    switch (requestCode) {
                        case TASK1_CODE:
                            tvTask1.setText("Task 1 started");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task 2 started");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task 3 started");
                            break;
                    }
                }

                if (status == STATUS_FINISH) {
                    switch (requestCode) {
                        case TASK1_CODE:
                            tvTask1.setText("Task 1 finished. Result: " + result);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task 2 finished. Result: " + result);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task 3 finished. Result: " + result);
                            break;
                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    public void onClickStart(View v) {
        Intent intent;

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_TASK, TASK1_CODE);
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK2_CODE);
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK3_CODE);
        startService(intent);
    }
}
