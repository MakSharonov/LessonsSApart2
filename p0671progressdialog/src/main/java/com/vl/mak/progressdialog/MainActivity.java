package com.vl.mak.progressdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case(R.id.btnDefault):
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setButton(ProgressDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                pd.show();
                break;
            case(R.id.btnHoriz):
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(12048);
                pd.setIndeterminate(true);
                pd.show();

                h = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pd.dismiss();
                        }
                    }
                };

                h.sendEmptyMessageDelayed(0, 2000);
                break;
        }
    }
}
