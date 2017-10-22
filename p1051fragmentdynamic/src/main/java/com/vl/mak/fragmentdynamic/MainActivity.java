package com.vl.mak.fragmentdynamic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

    FragmentTransaction fragmentTransaction;
    CheckBox chbStack;
    Fragment1 frag1;
    Fragment2 frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        chbStack = findViewById(R.id.chbStack);
    }

    void onClick(View v) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btnAdd:
                fragmentTransaction.add(R.id.frgmCont, frag1);
                break;
            case R.id.btnRemove:
                fragmentTransaction.remove(frag1);
                break;
            case R.id.btnReplace:
                fragmentTransaction.replace(R.id.frgmCont, frag2);
                break;
        }
        if (chbStack.isChecked()) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
