package com.example.android_tf1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private TextView emptypage;

    private RecyclerView ourdoes;
    private ArrayList<Frag1_3> list=new ArrayList<Frag1_3>();
    private Adapter1 adapter1;

    public Frag1_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1_1 newInstance( String param1, String param2 ) {
        Frag1_1 fragment = new Frag1_1();
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
        super.onCreate(savedInstanceState);
        view=inflater.inflate(R.layout.fragment_frag1_1,container,false);
        emptypage = view.findViewById(R.id.emptypage);
        //버큰클릭시 투두추가로이동
        ImageButton plusButton;
        plusButton=(ImageButton) view.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_2()).commit();
            }
        });

        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_frag1_1,container,false);
        ourdoes=(RecyclerView) rootView.findViewById(R.id.ourdoes);

        adapter1=new Adapter1(getActivity(),list);
        ourdoes.setLayoutManager(new LinearLayoutManager(getActivity()));
        ourdoes.setAdapter(adapter1);

        // working with data
        //ourdoes = view.findViewById(R.id.ourdoes);
        //ourdoes.setLayoutManager(new LinearLayoutManager(this));
        //list = new ArrayList<Frag1_3>();
        return view;
    }
}