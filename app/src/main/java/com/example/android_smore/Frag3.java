package com.example.android_smore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag3 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.frag3, container, false);

        //추가버튼 동작
        ImageButton btn1;
        btn1 = (ImageButton) view.findViewById(R.id.add_c_list);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag3_1()).commit();
            }
        });

        return view;
    }
}

//db에 저장된 챌린지 가져와서 프로그래스바로 나타내기
//프로그래스 바 클릭 -> 세부정보 창 이동 동작 구현