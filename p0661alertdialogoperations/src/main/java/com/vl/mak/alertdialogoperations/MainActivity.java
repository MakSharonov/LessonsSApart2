package com.vl.mak.alertdialogoperations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ma_log";
    final int DIALOG = 1;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void met1() {
        removeDialog(DIALOG);
    }

    void met2() {
        showDialog(DIALOG);
    }

    public void onclick(View v) {
        showDialog(DIALOG);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                met1();
            }
        }, 2000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                met2();
            }
        }, 4000);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LOG_TAG, "create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("Ok", null);
            dialog = adb.create();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Log.d(LOG_TAG, "show");
                }
            });

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.d(LOG_TAG, "cancel");
                }
            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Log.d(LOG_TAG, "dismiss");
                }
            });


            return dialog;
        }
        return super.onCreateDialog(id);
    }
}
