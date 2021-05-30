package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<updateData> update;

    public listAdapter (Context context, ArrayList<updateData> data) {
        mContext = context;
        update= data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return update.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public updateData getItem(int position) {
        return update.get(position);
    }

    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview, null);

        TextView TextbookName = (TextView)view.findViewById(R.id.name);
        TextbookName.setText(update.get(position).getName());


        return view;
    }

}
