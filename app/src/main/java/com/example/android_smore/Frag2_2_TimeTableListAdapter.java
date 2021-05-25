package com.example.android_smore;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Frag2_2_TimeTableListAdapter extends BaseAdapter {

    private Context context;
    private List<Frag2_2_List> tableList;

    public Frag2_2_TimeTableListAdapter(Context context, List<Frag2_2_List> tableList) {
        this.context = context;
        this.tableList = tableList;
    }

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int i) {
        return tableList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.frag2_2_list,null);
        TextView nameText = (TextView) v.findViewById(R.id.name_text);

        nameText.setText(tableList.get(i).getName());

        v.setTag(tableList.get(i).getName());
        return v;
    }
}
