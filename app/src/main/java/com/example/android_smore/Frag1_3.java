package com.example.android_smore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1_3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //여기부터붙여넣음
    String titledoes;
    String datedoes;
    String descdoes;

    public Frag1_3( String titledoes, String datedoes, String descdoes ) {
        this.titledoes = titledoes;
        this.datedoes = datedoes;
        this.descdoes = descdoes;
    }

    public String getTitledoes() {

        return titledoes;
    }

    public void setTitledoes( String titledoes ) {

        this.titledoes = titledoes;
    }

    public String getDatedoes() {

        return datedoes;
    }

    public void setDatedoes( String datedoes ) {

        this.datedoes = datedoes;
    }

    public String getDescdoes() {

        return descdoes;
    }

    public void setDescdoes( String descdoes ) {

        this.descdoes = descdoes;
    }


    public Frag1_3() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1_3.
     */
    // TODO: Rename and change types and number of parameters
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


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag1_3, container, false);
    }
}