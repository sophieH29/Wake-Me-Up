package com.example.android.wakemeup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

/**
 * Created by Sofia on 3/17/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {

    Context mContext;
    String[] mList;
    int selectedIndex = -1;

    public MyArrayAdapter(Context context, String[] list) {

        super(context, R.layout.music_item, R.id.txtitem, list);
        mContext = context;
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = super.getView(position, convertView, parent);
        RadioButton rbSelect = (RadioButton) convertView
                .findViewById(R.id.radio_button);

        if (selectedIndex == position) {
            rbSelect.setChecked(true);
        } else {
            rbSelect.setChecked(false);
        }

        return convertView;
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
    }
}
