package com.vl.mak.fragmentactivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements Fragment2.SomeEventListener {

    @Override
    public void someEvent(String s) {
        ((TextView) getFragmentManager().findFragmentById(R.id.fragment1).getView().findViewById(R.id.textView))
                .setText("Text from fragment two: " + s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment2 fragment2 = new Fragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment2, fragment2);
        ft.commit();
    }

    void onClick(View v) {
        ((TextView) getFragmentManager().findFragmentById(R.id.fragment1).getView().findViewById(R.id.textView))
                .setText("Access to Fragment one from activity");
        ((TextView) getFragmentManager().findFragmentById(R.id.fragment2).getView().findViewById(R.id.textView))
                .setText("Access to Fragment two from activity");
    }
}
