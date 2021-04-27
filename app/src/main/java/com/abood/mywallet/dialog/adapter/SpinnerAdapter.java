package com.abood.mywallet.dialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abood.mywallet.R;
import com.abood.mywallet.databinding.ItemSpinnerBinding;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter {

    ArrayList<String> items;
    Context context;

    public SpinnerAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = ItemSpinnerBinding.inflate(LayoutInflater.from(context)).getRoot();
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(items.get(position));
        return view;
    }


}
