package com.vl.mak.servicenotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tv;
    Intent intent;

    final static String FILENAME_PARAM = "filename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        intent = new Intent(this, MyService.class);

        String fileName = getIntent().getStringExtra(FILENAME_PARAM);

        if (!TextUtils.isEmpty(fileName)) {
            tv.setText(fileName);
        }
    }

    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickStop(View v) {
        stopService(intent);
    }
}
