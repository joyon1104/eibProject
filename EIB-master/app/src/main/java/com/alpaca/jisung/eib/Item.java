package com.alpaca.jisung.eib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by koserim on 2018. 3. 22..
 */

public class Item extends ArrayAdapter<String>{


    public Item(Context context, String[] name) {
        super(context, R.layout.image_layout, name);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View view = imageInflater.inflate(R.layout.image_layout, parent, false);
        String name = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.itemName);
        textView.setText(name);
        return view;
    }

}

