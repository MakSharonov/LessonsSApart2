package com.vl.mak.headerfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ma_log";

    String[] data = new String[] { "one", "two", "three", "four", "five" };
    ListView lvMain;
    ArrayAdapter<String> adapter;

    View header1;
    View header2;

    View footer1;
    View footer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");

        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    public void onClick(View v) {
        Object obj;
        HeaderViewListAdapter hvlAdapter = (HeaderViewListAdapter) lvMain.getAdapter();
        obj = hvlAdapter.getItem(1);
        Log.d(LOG_TAG, "get item 1 " + obj.toString());
        obj = hvlAdapter.getItem(4);
        Log.d(LOG_TAG, "get item 4 " + obj.toString());

        ArrayAdapter<String> alAdapter = (ArrayAdapter<String>) hvlAdapter.getWrappedAdapter();
        obj = alAdapter.getItem(1);
        Log.d(LOG_TAG, "get wrapped item 1 " + obj.toString());
        obj = alAdapter.getItem(4);
        Log.d(LOG_TAG, "get wrapped item 4 " + obj.toString());
    }

    public void fillList() {
        try {
            lvMain.setAdapter(adapter);
            lvMain.addHeaderView(header1);
            lvMain.addHeaderView(header2, "bibi", false);
            lvMain.addFooterView(footer1);
            lvMain.addFooterView(footer2, "meme", false);
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }
}
