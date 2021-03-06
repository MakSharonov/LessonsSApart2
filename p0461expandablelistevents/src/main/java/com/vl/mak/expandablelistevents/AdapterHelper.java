package com.vl.mak.expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mak on 05.06.2016.
 */
public class AdapterHelper {

    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";

    String[] groups = new String[] {"HTC", "Samsung", "LG"};

    String[] phonesHTC = new String[] {"h1", "h2", "h3", "h4"};
    String[] phonesSamsung = new String[] {"s1", "s2", "s3"};
    String[] phonesLG = new String[] {"l1", "l2", "l3", "l4", "l5"};

    ArrayList<Map<String,String>> groupData;
    ArrayList<Map<String,String>> childDataItem;
    ArrayList<ArrayList<Map<String,String>>> childData;

    Map<String,String> m;

    Context ctx;

    AdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter() {
        
        groupData = new ArrayList<Map<String, String>>();
        for (String group:groups) {
            m = new HashMap<String,String>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }

        String[] groupFrom = new String[] { ATTR_GROUP_NAME };
        int[] groupTo = new int[] { android.R.id.text1 };

        childData = new ArrayList<ArrayList<Map<String,String>>>();

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone:phonesHTC) {
            m = new HashMap<String,String>();
            m.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone:phonesSamsung) {
            m = new HashMap<String,String>();
            m.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone:phonesLG) {
            m = new HashMap<String,String>();
            m.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String[] childFrom = new String[] { ATTR_PHONE_NAME };
        int[] childTo = new int[] { android.R.id.text1 };

        adapter = new SimpleExpandableListAdapter(ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String,String>)adapter.getGroup(groupPos)).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos) {
        return ((Map<String,String>)adapter.getChild(groupPos, childPos)).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }

}
