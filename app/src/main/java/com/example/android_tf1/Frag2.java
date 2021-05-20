package com.example.android_tf1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


// 시간표 화면
public class Frag2 extends Fragment {

    private ListView noticeListView;
    private Frag2_NoticeListAdapter adapter;
    private List<Frag2_Notice> noticeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag2, container, false);

        noticeListView=(ListView)v.findViewById(R.id.noticeListView);
        noticeList=new ArrayList<Frag2_Notice>();
        noticeList.add(new Frag2_Notice("오픈소스전문프로젝트","천은정","09:00","10:00"));
        noticeList.add(new Frag2_Notice("오픈소스전문프로젝트","천은정","09:00","10:00"));
        noticeList.add(new Frag2_Notice("오픈소스전문프로젝트","천은정","09:00","10:00"));
        noticeList.add(new Frag2_Notice("오픈소스전문프로젝트","천은정","09:00","10:00"));
        noticeList.add(new Frag2_Notice("오픈소스전문프로젝트","천은정","09:00","10:00"));

        adapter=new Frag2_NoticeListAdapter(getActivity(),noticeList);
        noticeListView.setAdapter(adapter);

        //final Button scheduleButton=(Button)v.findViewById(R.id.scheduleButton);
        final LinearLayout notice=(LinearLayout)v.findViewById(R.id.notice);

        ImageButton addtimetablebtn;
        ImageButton showtimetablebtn;
        addtimetablebtn = (ImageButton) v.findViewById(R.id.add_timetable_button);
        showtimetablebtn = (ImageButton) v.findViewById(R.id.show_timetable_button);

        addtimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_1());
                fragmentTransaction.commit();
            }
        });

        showtimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_2());
                fragmentTransaction.commit();
            }
        });
        /*scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);    //공지사항 보여지지 않음

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_2());
                fragmentTransaction.commit();
            }
        });*/
        return v;
    }

}
