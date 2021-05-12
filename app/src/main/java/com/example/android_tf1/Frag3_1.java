package com.example.android_tf1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


///datepicker
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.widget.ListView;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag3_1#newInstance} factory method to
 * create an instance of this fragment.
 */


//챌린지 추가화면의 동작


public class Frag3_1 extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public Frag3_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag3_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag3_1 newInstance(String param1, String param2) {
        Frag3_1 fragment = new Frag3_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void updateDate1(){
        String format = "YYYY/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        SDate.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    public void updateDate2(){
        String format = "YYYY/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        EDate.setText(simpleDateFormat.format(myCalendar.getTime()));
    }


    //시작날짜 종료날짜 설정 부분
    Button SDate, EDate;
    Calendar myCalendar = Calendar.getInstance();

    //기본값으로 오늘 날짜
    DatePickerDialog.OnDateSetListener setDate1= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH,i1);
            myCalendar.set(Calendar.DAY_OF_MONTH,i2);
            updateDate1();
        }
    };

    DatePickerDialog.OnDateSetListener setDate2= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH,i1);
            myCalendar.set(Calendar.DAY_OF_MONTH,i2);
            updateDate2();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag3_1, container, false);

        //챌린지 추가창 나가기 버튼 동작
        ImageButton btn1;

        SDate = view.findViewById(R.id.SDate);
        EDate = view.findViewById(R.id.EDate);

        Date currentTime = Calendar.getInstance().getTime();
        SDate.setText(new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault()).format(currentTime));
        EDate.setText(new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault()).format(currentTime));

        btn1 = (ImageButton) view.findViewById(R.id.quit_c_list);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag3()).commit();
            }
        });

        SDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), setDate1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        EDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), setDate2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }
}

//종료날짜-시작날짜 이전 선택 불가

//맨 하단에 추가 버튼 -> DB에 저장, 챌린지 메인 화면으로 이동
//챌린지 세부 목표 추가하면 하단에 리스트 생성, 최대 10개까지 생성
//세부 목표가 1미만일 시 저장불가
//체크리스트 옆에 x 버튼(baseline_remove_circle_outline_24)도 함께 생성 x 버튼 클릭시 리스트 삭제