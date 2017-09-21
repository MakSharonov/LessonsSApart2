package com.vl.mak.servicebackpendingintent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    final static int TASK1_CODE = 1;
    final static int TASK2_CODE = 2;
    final static int TASK3_CODE = 3;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    TextView tvTask1, tvTask2, tvTask3;

    PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = findViewById(R.id.tvTask1);
        tvTask2 = findViewById(R.id.tvTask2);
        tvTask3 = findViewById(R.id.tvTask3);
    }

    public void onClickStart(View v) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == STATUS_START) {
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

        if (resultCode == STATUS_FINISH) {
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task 1 finished");
                    break;
            }
        }
    }
}
