package com.example.android_smore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


///datepicker
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.widget.ListView;

import com.example.android_smore.Model.ChallengeModel;
import com.example.android_smore.Model.ChallengeTaskModel;
import com.example.android_smore.Model.Frag3DataModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag3_1#newInstance} factory method to
 * create an instance of this fragment.
 */


//챌린지 추가화면의 동작


public class Frag3_1 extends Fragment {


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    private RecyclerView recyclerView;
    private Frag3_1Adapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Frag3DataModel> frag3DataModels;
    private int count = -1;

    public Frag3_1() {
        // Required empty public constructor
    }
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
    Button btnAdd;
    EditText etTitle;
    EditText etMemo;
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
        view = inflater.inflate(R.layout.frag3_1, container, false);

        //챌린지 추가창 나가기 버튼 동작
        ImageButton btn1;
        ImageButton T_Add;
        EditText GetValue;


        etTitle = view.findViewById(R.id.c_title);
        SDate = view.findViewById(R.id.SDate);
        EDate = view.findViewById(R.id.EDate);
        btnAdd = view.findViewById(R.id.btn_Ch_Add);
        etMemo = view.findViewById(R.id.add_c_list_memo);
        recyclerView = view.findViewById(R.id.recyclerview);
        T_Add = view.findViewById(R.id.btn_Ch_T_Add);
        GetValue = view.findViewById(R.id.add_c_list_task);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        frag3DataModels = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Frag3_1Adapter(getActivity(),frag3DataModels);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        T_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                Frag3DataModel frag3DataModel = new Frag3DataModel(GetValue.getText().toString());
                frag3DataModels.add(frag3DataModel);
                adapter.notifyDataSetChanged();
            }
        });


        Date currentTime = Calendar.getInstance().getTime();
        SDate.setText(new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault()).format(currentTime));
        EDate.setText(new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault()).format(currentTime));

        btn1 = (ImageButton) view.findViewById(R.id.quit_c_list);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFinish();
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

    /**
     * 챌린지 정보 추가, 예외처리
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.e("::::::::::사이즈 " + adapter.getItemList().size());
                if(adapter.getItemList().size() <= 0){
                    Toast.makeText(getActivity(), "최소 1개이상 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isEmtpy(etTitle)){
                    Toast.makeText(getActivity(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ChallengeTaskModel> list = new ArrayList<>();

                for(int i = 0 ; i < adapter.getItemList().size() ; i ++){
                    Frag3DataModel item  = adapter.getItemList().get(i);
                    list.add(new ChallengeTaskModel(item.getList(),false));
                }

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String title = etTitle.getText().toString();
                String startDate = SDate.getText().toString();
                String endDate = EDate.getText().toString();
                String memo = etMemo.getText() == null ? "" : etMemo.getText().toString();

                ChallengeModel model = new ChallengeModel(uid,title,startDate,endDate,memo,list);
                db.collection("Challenges").add(model).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        L.e(":::::::::::::::::added " + documentReference.getId());
                        Toast.makeText(getActivity(), "추가 되었습니다.", Toast.LENGTH_SHORT).show();
                        onFinish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        L.e(":::::::::::::::::error " + e);
                    }
                });
            }
        });
    }

    //필수 입력 사항에 대한 예외처리
    private boolean isEmtpy(EditText editText) {
        return TextUtils.isEmpty(editText.getText()) || editText.getText().toString().equalsIgnoreCase("");
    }

    private void onFinish() {
        getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag3()).commit();
    }
}