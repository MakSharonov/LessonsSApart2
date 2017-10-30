package com.vl.mak.dialogfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    DialogFragment dialog1;
    DialogFragment dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog1 = new Dialog1();
        dialog2 = new Dialog2();
    }

    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDialog1:
                dialog1.show(getFragmentManager(), "dialog1");
                break;
            case R.id.btnDialog2:
                dialog2.show(getFragmentManager(), "dialog2");
                break;
            default:
                break;
        }
    }
}
