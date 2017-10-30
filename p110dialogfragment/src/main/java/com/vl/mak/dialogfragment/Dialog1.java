package com.vl.mak.dialogfragment;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Dialog1 extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    public Dialog1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!!!");
        View v = inflater.inflate(R.layout.fragment_dialog1, container,false);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        v.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button)view).getText());
        dismiss(); // close the dialog
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}
