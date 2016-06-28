package com.vl.mak.alertdialogitems;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ma_log";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    int cnt = 0;
    DB db;
    Cursor cursor;

    String data[] = { "one", "two", "three", "four" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View v){
        changeCount();
        switch (v.getId()) {
            case (R.id.btnItems):
                showDialog(DIALOG_ITEMS);
                break;
            case (R.id.btnAdapter):
                showDialog(DIALOG_ADAPTER);
                break;
            case (R.id.btnCursor):
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
                adb.setItems(data, myClickListener);
                break;
            case (DIALOG_ADAPTER):
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter, myClickListener);
                break;
            case (DIALOG_CURSOR):
                adb.setTitle(R.string.cursor);
                adb.setCursor(cursor, myClickListener, DB.COLUMN_TXT);
                break;
        }
        return adb.create();
    }

//    @Override
//    protected void onPrepareDialog(int id, Dialog dialog) {
//        AlertDialog alertDialog = (AlertDialog)dialog;
//        ListAdapter listAdapter = alertDialog.getListView().getAdapter();
//
//        switch (id){
//            case DIALOG_ITEMS:
//            case DIALOG_ADAPTER:
//                if (listAdapter instanceof BaseAdapter) {
//                    ((BaseAdapter) listAdapter).notifyDataSetChanged();
//                }
//                break;
//            case DIALOG_CURSOR:
//                break;
//        }
//    }



    AlertDialog.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d(LOG_TAG, "which = " + which);
        }
    };

    void changeCount() {
        cnt++;
        data[3] = String.valueOf(cnt);
        db.ChangeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
