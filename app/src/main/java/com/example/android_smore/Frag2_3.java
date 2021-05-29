package com.example.android_smore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Frag2_3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView timetableListView;
    private Frag2_3_TimeTableListAdapter adapter;
    private List<Frag2_3_List> timetableList;

    public Frag2_3() {
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
    public static Frag2_3 newInstance(String param1, String param2) {
        Frag2_3 fragment = new Frag2_3();
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
        View v=inflater.inflate(R.layout.frag2_3, container, false);
        final EditText timetableeditText=new EditText(getContext());

        ImageButton addtimetablenamebtn = (ImageButton) v.findViewById(R.id.add_timetablename_button);

        // Firebase code
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getEmail();

        // Timetable list 목록 추가
        timetableListView=(ListView)v.findViewById(R.id.timetableListView);
        timetableList=new ArrayList<Frag2_3_List>();


        db.collection("Timetable")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.get("id").toString().equals(uid)) {
                                    String listdata = document.get("tablename").toString();
                                    Log.d(TAG,document.get("id").toString()+"_"+listdata);
                                    timetableList.add(new Frag2_3_List(listdata));
                                }
                            }
                            adapter = new Frag2_3_TimeTableListAdapter(getActivity(), timetableList);
                            timetableListView.setAdapter(adapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

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
                            // 추가 선택시
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String timetablename = timetableeditText.getText().toString();

                                // firebase data put
                                String docid = timetablename.concat("_"+uid);
                                Frag2_TableData data = new Frag2_TableData(timetablename,uid,true,null);

                                // Data 추가
                                db.collection("Timetable").document(docid)
                                        .set(data)
                                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG,"DocumentSnapshot successfully written!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener(){
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG,"Error writing document",e);
                                            }
                                        });

                                timetableList.add(new Frag2_3_List(timetablename));
                                adapter = new Frag2_3_TimeTableListAdapter(getActivity(), timetableList);
                                timetableListView.setAdapter(adapter);


                                // select 변경으로 입력된 시간표 선택
                                db.collection("Timetable")
                                        .whereNotEqualTo("select",false)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()){
                                                    for(QueryDocumentSnapshot document : task.getResult()){
                                                        Log.d(TAG,"select = "+document.getId()+"=>"+document.getData());
                                                        if(docid.equals(document.getId())){
                                                            Log.d(TAG,"추가된 시간표 : "+document.getId());
                                                            continue;
                                                        }
                                                        else {
                                                            // 현재로그인된 id가 포함된 document만
                                                            if (document.get("id").toString().equals(uid)) {
                                                                Log.d(TAG, "선택 없어질 시간표 : " + document.getId());
                                                                DocumentReference changeref = db.collection("Timetable").document(document.getId());
                                                                changeref.update("select", false)
                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                                            }

                                                                        })
                                                                        .addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {
                                                                                Log.w(TAG, "Error updating document", e);
                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    }
                                                }
                                                else{
                                                    Log.d(TAG,"Error getting documents : ",task.getException());
                                                }
                                            }
                                        });

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