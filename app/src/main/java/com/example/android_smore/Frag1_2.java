package com.example.android_smore;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Frag1_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Frag1_2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private Context context;
    TextView titlepage, addtitle, adddesc, adddate;
    EditText titledoes, datedoes, descdoes;
    Button btnSaveTask, btnCancel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Integer doesNum = new Random().nextInt();
    String keydoes = Integer.toString(doesNum);

    public Frag1_2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Frag1_2 newInstance( String param1, String param2 ) {
        Frag1_2 fragment = new Frag1_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view=inflater.inflate(R.layout.frag1_2,container,false);
        //context=container.getContext(); //DB 전 확인용

        titlepage=view.findViewById(R.id.titlepage);

        addtitle=view.findViewById(R.id.titlepage);
        adddate = view.findViewById(R.id.adddate);
        adddesc = view.findViewById(R.id.adddesc);


        titledoes = view.findViewById(R.id.titledoes);
        datedoes = view.findViewById(R.id.datedoes);
        descdoes = view.findViewById(R.id.descdoes);


        btnSaveTask = view.findViewById(R.id.btnSaveTask);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                //insert data to database
                String keydoes = UUID.randomUUID().toString(); //random id
                Map<String, Object> todo = new HashMap<>();
                todo.put("titledoes", titledoes);
                todo.put("datedoes", datedoes);
                todo.put("descdoes", descdoes);
                todo.put("keydoes", keydoes);

                db.collection("ToDoList").document(keydoes)
                        .set(todo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.main_frame, new Frag1_1());
                                fragmentTransaction.commit();
                                Toast.makeText(getActivity(),"새로운 ToDo가 추가되었습니다.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( @NonNull Exception e ) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Frag1_1()).commit();
            }
        });

        ImageButton closeButton;
        closeButton=(ImageButton) view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Frag1_1()).commit();
            }
        });

        return view;
}
//    private void setData( String titledoes, String datedoes , String descdoes ) {
//        //insert data to database
//        String keydoes = UUID.randomUUID().toString(); //random id
//        Map<String, Object> todo = new HashMap<>();
//        todo.put("titledoes", titledoes);
//        todo.put("datedoes", datedoes);
//        todo.put("descdoes", descdoes);
//        todo.put("keydoes", keydoes);
//
//        db.collection("ToDoList").document(keydoes)
//                .set(todo)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess( Void aVoid ) {
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
//                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.main_frame, new Frag1_1());
//                        fragmentTransaction.commit();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure( @NonNull Exception e ) {
//                        Log.w(TAG, "Error writing document", e);
//                    }
//                });
//        }

}