package com.vl.mak.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ma_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View v) {
        MyObject myObject = new MyObject("lolo", 77);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(MyObject.class.getCanonicalName(), myObject);
        Log.d(LOG_TAG, "startActivity");
        startActivity(intent);
    }
}
