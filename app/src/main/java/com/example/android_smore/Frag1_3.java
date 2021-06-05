package com.example.android_smore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Frag1_3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    public String idUpdate = ""; //업데이트 해야하는 item의 id
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String keydoes=""; //업데이트 해야하는 item의 keydoes
    final  String keykeydoes =getArguments().getString("keydoes");
    DocumentReference req;
    EditText titledoes, descdoes, datedoes;
    Button btnSaveUpdate, btnDelete;
    //DatabaseReference reference;

    public Frag1_3() {

    }

    public static Frag1_3 newInstance( String param1, String param2 ) {
        Frag1_3 fragment = new Frag1_3();
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        view=inflater.inflate(R.layout.frag1_3,container,false);
        req = db.collection("ToDoList").document(mParam1);

        titledoes = view.findViewById(R.id.titledoes);
        descdoes = view.findViewById(R.id.descdoes);
        datedoes = view.findViewById(R.id.datedoes);

        btnSaveUpdate = view.findViewById(R.id.btnSaveTask);
        btnDelete = view.findViewById(R.id.btnCancel);
        ImageButton closeButton;
        closeButton=(ImageButton) view.findViewById(R.id.closeButton);

        titledoes.setText(getArguments().getString("titledoes"));
        descdoes.setText(getArguments().getString("descdoes"));
        datedoes.setText(getArguments().getString("datedoes"));

        btnSaveUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick( View view ) {
                updateData(titledoes.getText().toString(),descdoes.getText().toString(),datedoes.getText().toString());
            }
        });


        btnDelete.setOnClickListener(view -> req.delete().addOnSuccessListener(aVoid -> {
            onFinish();
            Toast.makeText(getActivity(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show()));


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Frag1_1()).commit();

            }
        });

        return view;
    }

    private void onFinish() {
        getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_1()).commit();
    }

    private void updateData( String titledoes, String descdoes, String datedoes ) {
        db.collection("ToDoList").document(idUpdate)
                .update("titledoes",titledoes,"descdoes",descdoes,"datedoes",datedoes)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid ) {
                        Toast.makeText(getActivity(),"수정이 완료되었습니다",Toast.LENGTH_SHORT).show();
                    }
                });
        //실시간 데이터 refresh
        db.collection("ToDoList").document(idUpdate)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent( @Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error ) {
                        FragmentActivity activitiy = null;
                        Frag1_1 fragment = (Frag1_1) activitiy.getSupportFragmentManager().findFragmentById(R.id.fragment);
                        fragment.loadData();
                    }
                });
    }
}