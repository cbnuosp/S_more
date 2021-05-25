package com.example.android_smore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Frag2_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView timetableListView;
    private Frag2_2_TimeTableListAdapter adapter;
    private List<Frag2_2_List> timetableList;

    public Frag2_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag2_3.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag2_2 newInstance(String param1, String param2) {
        Frag2_2 fragment = new Frag2_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag2_2, container, false);
        final EditText timetableeditText=new EditText(getContext());

        ImageButton addtimetablenamebtn = (ImageButton) v.findViewById(R.id.add_timetablename_button);

        timetableListView=(ListView)v.findViewById(R.id.timetableListView);
        timetableList=new ArrayList<Frag2_2_List>();
        timetableList.add(new Frag2_2_List("1학년 1학기"));
        timetableList.add(new Frag2_2_List("1학년 2학기"));
        timetableList.add(new Frag2_2_List("2학년 1학기"));
        timetableList.add(new Frag2_2_List("2학년 2학기"));
        timetableList.add(new Frag2_2_List("3학년 1학기"));
        timetableList.add(new Frag2_2_List("3학년 2학기"));


        adapter=new Frag2_2_TimeTableListAdapter(getActivity(),timetableList);
        timetableListView.setAdapter(adapter);

        addtimetablenamebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add TimeTable");
                builder.setMessage("추가할 시간표의 이름을 입력해주세요.");
                builder.setView(timetableeditText);

                builder.setPositiveButton("추가",
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 추가 선택시
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


        return v;
    }
}