package com.vl.mak.expandablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String[] groups = new String[] {"HTC", "Samsung", "LG"};

    String[] phonesHTC = new String[] {"h1", "h2", "h3", "h4"};
    String[] phonesSamsung = new String[] {"s1", "s2", "s3"};
    String[] phonesLG = new String[] {"l1", "l2", "l3", "l4", "l5"};

    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String,String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String,String>>> childData;

    // список атрибутов группы или элементов
    Map<String,String> m;

    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupData = new ArrayList<Map<String, String>>();
        for (String group:groups) {
            m = new HashMap<String,String>();
            m.put("groupName", group);
            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String [] groupFrom = new String[] {"groupName"};

        // список ИД вью, в которые будут помещены атрибуты групп
        int[] groupTo = new int[] {android.R.id.text1};

        childData = new ArrayList<ArrayList<Map<String,String>>>();

        // первая группа
        childDataItem = new ArrayList<Map<String, String>>();
        for(String phone:phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // вторая группа
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone:phonesSamsung) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // третья группа
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone:phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String[] childFrom = new String[] {"phoneName"};
        int[] childTo = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}
