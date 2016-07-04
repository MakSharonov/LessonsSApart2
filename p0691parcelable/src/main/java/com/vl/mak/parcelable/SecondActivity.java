package com.vl.mak.parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    final String LOG_TAG = "ma_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(LOG_TAG, "getParcelableExtra()");
        MyObject myObject = (MyObject) getIntent().getParcelableExtra(MyObject.class.getCanonicalName());
        Log.d(LOG_TAG, "s = " + myObject.s + " i = " + myObject.i);
    }
}
