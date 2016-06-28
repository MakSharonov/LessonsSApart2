package com.vl.mak.alertdialogprepare;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "ma_log";
    final int DIALOG = 1;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View v) {
        showDialog(DIALOG);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        Log.d(LOG_TAG, "onPrepare");
        if (id == DIALOG) {
            ((AlertDialog) dialog).setMessage(sdf.format(System.currentTimeMillis()));
        }
        super.onPrepareDialog(id, dialog);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.d(LOG_TAG, "onCreate");
        if (id == DIALOG) {
            AlertDialog.Builder adb= new AlertDialog.Builder(this);
            adb.setTitle("Текущее время");
            adb.setMessage(sdf.format(System.currentTimeMillis()));
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
}
