package com.vl.mak.alertdialogitemsmulti;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ma_log";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 2;
    DB db;
    Cursor cursor;

    String data[] = { "one", "two", "three", "four" };
    boolean chkd[] = { false, true, true, false };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case (DIALOG_ITEMS):
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data, chkd, myItemsMultiClickListener);
                break;
            case (DIALOG_CURSOR):
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, myCursorMultiClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok, myClickListener);
        return adb.create();
    }

    AlertDialog.OnMultiChoiceClickListener myItemsMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.d(LOG_TAG, "which = " + which + " is checked = " + isChecked);
        }
    };

    AlertDialog.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            ListView lv = ((AlertDialog) dialog).getListView();
            Log.d(LOG_TAG, "which = " + which + " is checked = " + isChecked);
            db.ChangeRec(which, isChecked);
            cursor.requery();
        }
    };

    AlertDialog.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sba = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            for (int i=0; i<sba.size(); i++) {
                int key = sba.keyAt(i);
                if (sba.get(key)) {
                    Log.d(LOG_TAG, "checked: " + key);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
