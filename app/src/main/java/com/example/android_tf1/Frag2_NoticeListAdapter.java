package com.example.android_tf1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Frag2_NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<Frag2_Notice> noticeList;

    public Frag2_NoticeListAdapter(Context context, List<Frag2_Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.frag2_notice,null);
        TextView majorText = (TextView) v.findViewById(R.id.majorText);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView timestartText = (TextView) v.findViewById(R.id.timestartText);
        TextView timeendText = (TextView) v.findViewById(R.id.timeendText);

        majorText.setText(noticeList.get(i).getMajor());
        nameText.setText(noticeList.get(i).getName());
        timestartText.setText(noticeList.get(i).getTimestart());
        timeendText.setText(noticeList.get(i).getTimeend());

        v.setTag(noticeList.get(i).getMajor());
        return v;
    }
}
