package com.urjapawar.jagrukbharat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yash bafna on 7/23/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String title;
    private final String desc;

    public CustomAdapter(Activity context,
                      String head,String des) {
        super(context, R.layout.list_item);
        this.context = context;
        this.title = head;
        this.desc=des;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);

        TextView descr = (TextView) rowView.findViewById(R.id.desc);
        txtTitle.setText(title);
        descr.setText(desc);
        return rowView;
    }
}