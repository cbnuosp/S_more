package com.example.android_smore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Frag2_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;

    private Integer starttime, endtime;
    private String coursename,professorname,color,day;
    private String time="";
    private String data="";


    public Frag2_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag2_1.
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

        View v = inflater.inflate(R.layout.frag2_2, container, false);

        // Firebase code
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getEmail();

        Spinner spinnerstart=(Spinner)v.findViewById(R.id.spinner_start);
        Spinner spinnerend=(Spinner)v.findViewById(R.id.spinner_end);
        Spinner spinnerday=(Spinner)v.findViewById(R.id.spinner_day);
        Spinner spinnercolor=(Spinner)v.findViewById(R.id.spinner_color);

        EditText coursenametxt = (EditText)v.findViewById(R.id.add_coursename_text);
        EditText professornametxt = (EditText)v.findViewById(R.id.add_professorname_text);

        Button addbtn = (Button) v.findViewById(R.id.add_coursedata_button);

        ImageView previewimg = (ImageView) v.findViewById(R.id.preview_image);

        context = container.getContext();

        // ???????????? ?????? ?????? ???
        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // ???????????? ???????????? ????????? ?????? ????????? ??????, return
                if((coursenametxt.getText().toString().length()==0) ||(spinnerstart.getSelectedItemPosition()==0)
                        ||(spinnerend.getSelectedItemPosition()==0) ||(spinnercolor.getSelectedItemPosition()==0)
                        ||(spinnerday.getSelectedItemPosition()==0) ||(starttime>endtime)){
                    Log.v("???????????? ??????","?????? ??????");
                    // Toast message ?????? ??????
                    Toast.makeText(getActivity(), "?????? ????????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                }
                // ?????? ??????????????? ???
                else{
                    Log.v("???????????? ??????","?????? ??????");
                    time="";

                    coursename = coursenametxt.getText().toString();
                    professorname = professornametxt.getText().toString();
                    for(int i=starttime;i<=endtime;i++){
                        String temp = "["+i+"]";
                        time=time.concat(temp);
                    }

                    data=day+":"+time+"cn:"+coursename+"pn:"+professorname+"co:"+color;
                    Log.v("????????? ??????",data);

                    Frag2_Schedule scheduledata = new Frag2_Schedule();

                    // ????????? ???????????? ?????? ???
                    if(scheduledata.validate(day)==true) {
                        db.collection("Timetable")
                                .whereEqualTo("select",true)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for(QueryDocumentSnapshot document : task.getResult()) {
                                                if(document.get("id").toString().equals(uid)){  // ???????????? ???????????????
                                                    Log.v("????????????????????? : ",document.getId());

                                                    DocumentReference uidDocumentRef = db.collection("Timetable").document(document.getId());
                                                    uidDocumentRef.update("coursedata", FieldValue.arrayUnion(data));

                                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                    fragmentTransaction.replace(R.id.fragment, new Frag2_1());
                                                    fragmentTransaction.commit();
                                                }
                                            }
                                        }
                                        else{
                                            Log.d(TAG,"Error getting documents : ",task.getException());
                                        }
                                    }
                                });

                        Toast.makeText(getActivity(), "?????? ???????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.v("????????? ??????","??????, ?????? ???????????? ??????");
                        return;
                    }
                }
            }
        });

        // ?????? ?????? ?????? spinner
        spinnerstart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // i : item ??????, 1??? 09:00 9??? 17:00
                if (spinnerstart.getSelectedItemPosition() > 0) {
                    Log.v("?????? ?????? : ", spinnerstart.getSelectedItem().toString() + "_" + spinnerstart.getSelectedItemPosition());
                    starttime = spinnerstart.getSelectedItemPosition();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // ????????? ?????? ?????? spinner
        spinnerend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // i : item ??????, 1??? 10:00 9??? 18:00
                if (spinnerend.getSelectedItemPosition() > 0) {
                    Log.v("????????? ?????? : ",spinnerend.getSelectedItem().toString()+"_"+spinnerend.getSelectedItemPosition());
                    endtime = spinnerend.getSelectedItemPosition();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // ?????? ?????? spinner
        spinnerday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                // i : item ??????, 1??? ????????? 5??? ?????????
                if(spinnerday.getSelectedItemPosition()>0){
                    Log.v("?????? : ",spinnerday.getSelectedItem().toString()+"_"+spinnerday.getSelectedItemPosition());
                    day = spinnerday.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // ??? ?????? spinner
        spinnercolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /* i : item ??????
                   1??? LightBlue
                   2??? LightCoral
                   3??? LightCyan
                   4??? LightGray
                   5??? LightGreen
                   6??? LightPink
                   7??? LightSlateGray
                   8??? LightSalmon
                   9??? LightYellow
                   10??? LightSteelBlue */
                color = spinnercolor.getSelectedItem().toString();
                switch (spinnercolor.getSelectedItemPosition()) {
                    case 0:
                        break;
                    case 1:
                        previewimg.setImageResource(R.color.Red);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 2:
                        previewimg.setImageResource(R.color.Orange);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 3:
                        previewimg.setImageResource(R.color.Yellow);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 4:
                        previewimg.setImageResource(R.color.Green);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 5:
                        previewimg.setImageResource(R.color.SkyBlue);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 6:
                        previewimg.setImageResource(R.color.Indigo);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 7:
                        previewimg.setImageResource(R.color.Purple);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 8:
                        previewimg.setImageResource(R.color.LightPurple);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 9:
                        previewimg.setImageResource(R.color.LightPink);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                    case 10:
                        previewimg.setImageResource(R.color.Gray);
                        Log.v("??? : ",spinnercolor.getSelectedItem().toString()+"_"+spinnercolor.getSelectedItemPosition());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }
}