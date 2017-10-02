package com.vl.mak.multitouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener{

    TextView tvInfo;
    boolean isTouched;
    int lastDownIndex, lastUpIndex;
    StringBuilder sb;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        tvInfo = new TextView(this);
        tvInfo.setOnTouchListener(this);
        setContentView(tvInfo);

        sb = new StringBuilder();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getActionMasked();
        int pointerCount = motionEvent.getPointerCount();
        sb.setLength(0);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouched = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                lastDownIndex = motionEvent.getActionIndex();
                break;
            case MotionEvent.ACTION_UP:
                isTouched = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                lastUpIndex = motionEvent.getActionIndex();
                break;
        }
        if (isTouched) {
            for (int i=0; i<pointerCount; i++) {
                sb.append("index:" + i + ", id:" + motionEvent.getPointerId(i) + ", x:" + motionEvent.getX(i) +
                        ", y:" + motionEvent.getY(i) + "\n");
            }
            result = "Fingers touched: " + pointerCount + "\n" +
                    "Last touch index: " + lastDownIndex + "\n" +
                    "Last up index: " + lastUpIndex + "\n" +
                    sb.toString();
        } else {
            result = "";
        };
        tvInfo.setText(result);

        return true;
    }
}
