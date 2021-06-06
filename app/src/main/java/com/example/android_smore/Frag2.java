package com.example.android_smore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import static android.content.ContentValues.TAG;

// 시간표 화면
public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag2, container, false);

        //final Button scheduleButton=(Button)v.findViewById(R.id.scheduleButton);
        //final LinearLayout timetable=(LinearLayout)v.findViewById(R.id.timetable);

        ImageButton addtimetablebtn;
        ImageButton deletetimetablebtn;
        ImageButton timetablelistbtn;
        addtimetablebtn = (ImageButton) v.findViewById(R.id.add_timetable_button);
        deletetimetablebtn = (ImageButton) v.findViewById(R.id.delete_timetable_button);
        timetablelistbtn = (ImageButton) v.findViewById(R.id.timetablelist_button);

        final TextView timetablenametxt;
        TextView timetablehometxt;
        timetablenametxt = (TextView) v.findViewById(R.id.timetablename_text);
        timetablehometxt = (TextView) v.findViewById(R.id.timetablehome);

        // Firebase code
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getEmail();

        // Fragment
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new Frag2_1());
        fragmentTransaction.commit();

        timetablehometxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_1());
                fragmentTransaction.commit();
            }
        });

        addtimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_2());
                fragmentTransaction.commit();
            }
        });

        deletetimetablebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete TimeTable");
                builder.setMessage("시간표를 삭제하시겠습니까?");

                builder.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener(){

                            @Override
                            // 삭제 버튼
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 삭제 선택시
                                // 선택된 시간표 data 제거
                                db.collection("Timetable")
                                        .whereEqualTo("select",true)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()){
                                                    for(QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.v("선택된 문서",document.getId() + "=>" + document.getData());
                                                        if (document.get("id").toString().equals(uid)) {
                                                            Log.v("삭제될 문서",document.getId() + "=>" + document.getData());
                                                            db.collection("Timetable").document(document.getId())
                                                                    .delete()
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Log.w(TAG, "Error deleting document", e);
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                }
                                                else{
                                                    Log.d(TAG,"Error getting documents : ",task.getException());
                                                }
                                            }
                                        });
                                db.collection("Timetable")
                                        .whereEqualTo("id",uid)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if(task.isSuccessful()){
                                                    for(QueryDocumentSnapshot document : task.getResult()) {
                                                        db.collection("Timetable").document(document.getId())
                                                                .update("select",true)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Log.v("다음시간표 선택완료",document.getId());
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Log.v("다음시간표 선택실패","fail");
                                                                    }
                                                                });
                                                        break;
                                                    }
                                                }
                                                else{
                                                    Log.d(TAG,"Error getting documents : ",task.getException());
                                                }
                                            }
                                        });
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment, new Frag2_1());
                                fragmentTransaction.commit();

                                Toast.makeText(getActivity(), "시간표가 삭제 되었습니다.", Toast.LENGTH_SHORT).show();

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
                // Fragment
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Frag2_3());
                fragmentTransaction.commit();
            }
        });

        return v;
    }

}
