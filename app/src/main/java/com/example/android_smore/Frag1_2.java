package com.example.android_smore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    TextView titlepage, addtitle, adddesc, adddate;
    EditText titledoes, descdoes, datedoes;
    Button btnSaveTask, btnCancel;
    //DatabaseReference reference;
    //Integer doesNum = new Random().nextInt();
    //String keydoes = Integer.toString(doesNum);

    public Frag1_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1_2.
     */
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
        titlepage=view.findViewById(R.id.titlepage);

        addtitle=view.findViewById(R.id.titlepage);
        adddesc = view.findViewById(R.id.adddesc);
        adddate = view.findViewById(R.id.adddate);

        titledoes = view.findViewById(R.id.titledoes);
        descdoes = view.findViewById(R.id.descdoes);
        datedoes = view.findViewById(R.id.datedoes);

        btnSaveTask = view.findViewById(R.id.btnSaveTask);
        btnCancel = view.findViewById(R.id.btnCancel);

        ImageButton closeButton;
        closeButton=(ImageButton) view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Frag1()).commit();

                // insert data to database
//                reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").
//                        child("Does" + doesNum);
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        dataSnapshot.getRef().child("titledoes").setValue(titledoes.getText().toString());
//                        dataSnapshot.getRef().child("descdoes").setValue(descdoes.getText().toString());
//                        dataSnapshot.getRef().child("datedoes").setValue(datedoes.getText().toString());
//                        dataSnapshot.getRef().child("keydoes").setValue(keydoes);
//
//                        Intent a = new Intent(NewTaskAct.this,MainActivity.class);
//                        startActivity(a);
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
            }
        });

        return view;
    }
}