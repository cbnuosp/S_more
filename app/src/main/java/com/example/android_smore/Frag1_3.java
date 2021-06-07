package com.example.android_smore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
//    public String idUpdate = ""; //업데이트 해야하는 item의 id
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText titledoes, descdoes, datedoes;
    Button btnSaveUpdate, btnDelete;
    ImageButton closeButton;

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

    protected int LayoutRes() {
        return (R.layout.frag1_3);
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
        view = inflater.inflate(R.layout.frag1_3, container, false);

        titledoes = view.findViewById(R.id.titledoes);
        descdoes = view.findViewById(R.id.descdoes);
        datedoes = view.findViewById(R.id.datedoes);

        btnSaveUpdate = view.findViewById(R.id.btnSaveUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
        //ImageButton closeButton;
        closeButton = (ImageButton) view.findViewById(R.id.closeButton);

        //이전페이지에서 value 얻기
        titledoes.setText(getArguments().getString("titledoes"));
        descdoes.setText(getArguments().getString("descdoes"));
        datedoes.setText(getArguments().getString("datedoes"));

        //firebase
        final String keykeydoes = getArguments().getString("keydoes");


        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                final String titledata = titledoes.getText().toString();
                final String descdata = descdoes.getText().toString();
                final String datedata = datedoes.getText().toString();

                db.collection("ToDoList").document(keykeydoes)
                        .update("titledoes",titledata,"descdoes",descdata,"datedoes",datedata)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                Toast.makeText(getContext(), "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.main_frame, new Frag1_1());
                                fragmentTransaction.commit();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( @NonNull Exception e ) {
                                Toast.makeText(getContext(), "수정이 실패하였습니다", Toast.LENGTH_SHORT).show();
                            }
                        });
//                db.collection("ToDoList").document(keykeydoes)
//                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                            @Override
//                            public void onEvent( @Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error ) {
//                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.replace(R.id.main_frame, new Frag1_1());
//                                fragmentTransaction.commit();
//                            }
//                        });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                db.collection("ToDoList").document(keykeydoes)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                Toast.makeText(getActivity(), "삭제가 완료되었습니다", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.main_frame, new Frag1_1());
                                fragmentTransaction.commit();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( @NonNull Exception e ) {
                                Toast.makeText(getContext(), "삭제를 실패하였습니다", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_1()).commit();
            }
        });
        return view;
    }
}
