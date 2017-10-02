package com.vl.mak.touch;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    float x, y;
    String sDown, sMove, sUp;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        tvInfo = new TextView(this);
        tvInfo.setOnTouchListener(this);
        setContentView(tvInfo);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sDown = "Down: " + x + ", " + y;
                sMove = "";
                sUp = "";
                break;
            case MotionEvent.ACTION_MOVE:
                sMove = "Move: " + x + ", " + y;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                sUp = "Up: " + x + ", " + y;
        }
        tvInfo.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }
}
