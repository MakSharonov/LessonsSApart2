package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1cprog2 on 17.06.2016.
 */
public class BoxAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Product> objects;

    public BoxAdapter(Context ctx, ArrayList<Product> objects) {
        this.ctx = ctx;
        this.objects = objects;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price);
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.lo);

        CheckBox cbBox = (CheckBox) view.findViewById(R.id.cbBox);
        cbBox.setTag(position);
        cbBox.setChecked(p.box);

        cbBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getProduct((Integer) buttonView.getTag()).box = isChecked;
            }
        });

        return view;
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p:objects) {
            if (p.box) {
                box.add(p);
            }
        }
        return box;
    }

    Product getProduct(int position) {
        return (Product) getItem(position);
    }


}
