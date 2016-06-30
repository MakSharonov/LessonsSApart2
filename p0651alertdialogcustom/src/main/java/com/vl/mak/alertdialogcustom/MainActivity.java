package com.vl.mak.alertdialogcustom;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int DIALOG = 1;

    int btn;
    LinearLayout view;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    TextView tvTime;
    ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViews = new ArrayList<>(10);
    }

    public void onclick(View v) {
        btn = v.getId();
        showDialog(DIALOG);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            view = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog, null);
            adb.setView(view);
            tvTime = (TextView)view.findViewById(R.id.tvTime);
            tvCount = (TextView)view.findViewById(R.id.tvCount);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {
            // TextView tvTime = (TextView) dialog.getWindow().findViewById(R.id.tvTime); можно так
            tvTime.setText(sdf.format(System.currentTimeMillis()));
            if (btn == R.id.btnAdd) {
                TextView textView = new TextView(this);
                textViews.add(textView);
                textView.setText("This is #" + (textViews.size()));
                view.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } else if (btn == R.id.btnRemove) {
                if (textViews.size()>0) {
                    TextView textView = textViews.get(textViews.size() - 1);
                    view.removeView(textView);
                    textViews.remove(textView);
                }
            }
            tvCount.setText(String.valueOf(textViews.size()));
        }
    }
}
