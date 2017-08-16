package com.vl.mak.tabcontentfactory;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    final String TAB_TAG_1 = "Tag1";
    final String TAB_TAG_2 = "Tag2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TAB_TAG_1);
        tabSpec.setIndicator("Tab 1");
        tabSpec.setContent(tabContentFactory);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TAB_TAG_2);
        tabSpec.setIndicator("Tab 2");
        tabSpec.setContent(tabContentFactory);
        tabHost.addTab(tabSpec);

    }

    TabHost.TabContentFactory tabContentFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (tag.equals(TAB_TAG_1)) {
                return getLayoutInflater().inflate(R.layout.tab, null);
            } else if (tag.equals(TAB_TAG_2)) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText("Handmade");
                return tv;
            }
            return null;
        }
    };
}
