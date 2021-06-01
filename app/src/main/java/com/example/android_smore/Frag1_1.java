package com.example.android_smore;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1_1#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Frag1_1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View view;
    private TextView emptypage;
    private Button plusButton;

    // DatabaseReference reference;
    private RecyclerView ourdoes;
    private ArrayList<Frag1_3> list=new ArrayList<Frag1_3>();
    private Frag1_ToDoListAdapter adapter1;
    private LinearLayoutManager layoutManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1_1 newInstance(String param1, String param2) {
        Frag1_1 fragment = new Frag1_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Frag1_1() {
        // Required empty public constructor
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.frag1_1,container,false);

        emptypage = rootView.findViewById(R.id.emptypage);
        ImageButton plusButton;
        plusButton=(ImageButton) rootView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_2()).commit();
            }
        });

        ourdoes=(RecyclerView) rootView.findViewById(R.id.ourdoes);

        list = new ArrayList<Frag1_3>();
        ourdoes.setHasFixedSize(true);
        adapter1=new Frag1_ToDoListAdapter(getActivity(),list);
        ourdoes.setLayoutManager(new LinearLayoutManager(getActivity()));
        ourdoes.setAdapter(adapter1);




//        // get data from firebase
//        // FirebaseApp.initializeApp(this);
//        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // set code to retrive data and replace layout
//                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
//                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
//                {
//                    MyDoes p = dataSnapshot1.getValue(MyDoes.class);
//                    list.add(p);
//                }
//                doesAdapter = new DoesAdapter(MainActivity.this, list);
//                ourdoes.setAdapter(doesAdapter);
//                doesAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // set code to show an error
//                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
//            }
//        });
        return rootView;
    }
}