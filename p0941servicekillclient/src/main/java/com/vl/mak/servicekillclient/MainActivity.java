
package com.vl.mak.servicekillclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent in = new Intent("com.vl.mak.p0942servicekillserver.MyService").putExtra("name","value");
        in.setPackage("com.vl.mak");
        startService(in);
    }
}
