package com.example.android_smore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



// 시간표 화면
public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag2, container, false);

        //final Button scheduleButton=(Button)v.findViewById(R.id.scheduleButton);
        final LinearLayout timetable=(LinearLayout)v.findViewById(R.id.timetable);

        ImageButton addtimetablebtn;
        ImageButton deletetimetablebtn;
        ImageButton timetablelistbtn;
        addtimetablebtn = (ImageButton) v.findViewById(R.id.add_timetable_button);
        deletetimetablebtn = (ImageButton) v.findViewById(R.id.delete_timetable_button);
        timetablelistbtn = (ImageButton) v.findViewById(R.id.timetablelist_button);

        addtimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timetable.setVisibility(View.GONE);

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_1());
                fragmentTransaction.commit();
            }
        });

        deletetimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*timetable.setVisibility(View.GONE);

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_2());
                fragmentTransaction.commit();*/
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete TimeTable");
                builder.setMessage("시간표를 삭제하시겠습니까?");

                builder.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 삭제 선택시
                            }
                        });
                /*builder.setPositiveButton("취소",
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 취소 선택시
                            }
                        });*/
                builder.show();
            }
        });

        timetablelistbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timetable.setVisibility(View.GONE);

                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_2());
                fragmentTransaction.commit();
            }
        });

        return v;
    }

}
