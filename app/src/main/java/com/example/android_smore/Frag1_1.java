package com.example.android_smore;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_smore.Model.ToDoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1_1#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class Frag1_1 extends Fragment {
    private static final String TAG = "Frag1_1";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View view;

    public TextView emptypage,titledoes, descdoes, datedoes;
    Button plusButton;
    AlertDialog dialog;

    public String idUpdate = ""; //업데이트 해야하는 item의 id
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView ourdoes;
    ArrayList<ToDoModel> list;
    Frag1_ToDoListAdapter adapter1;
    LinearLayoutManager layoutManager;

    //DocumentReference req;

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

    public Frag1_1() {
        // Required empty public constructor
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag1_1, container, false);

     //   dialog=new SpotsDialog(this);
        emptypage = rootView.findViewById(R.id.emptypage);
        titledoes= rootView.findViewById(R.id.titledoes);
        descdoes= rootView.findViewById(R.id.descdoes);
        datedoes= rootView.findViewById(R.id.datedoes);
        ImageButton plusButton;
        plusButton = (ImageButton) rootView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag1_2()).commit();
            }
        });
        list = new ArrayList<ToDoModel>();
        adapter1 = new Frag1_ToDoListAdapter(getActivity(), list);
        ourdoes = (RecyclerView) rootView.findViewById(R.id.ourdoes);
        ourdoes.setHasFixedSize(true);
        ourdoes.setLayoutManager(new LinearLayoutManager(getActivity()));
        ourdoes.setAdapter(adapter1);

        loadData();

        return rootView;
    }

    public void loadData(){

        if(list.size()>0)
            list.clear(); //remove old value
       db.collection("ToDoList")
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete( @NonNull Task<QuerySnapshot> task ) {
                       for(DocumentSnapshot doc:task.getResult()){
                           ToDoModel todo = new ToDoModel(doc.getString("titledoes"),
                                 doc.getString("datedoes"),
                                 doc.getString("descdoes"),
                                 doc.getString("keydoes"));
                         list.add(todo);
                       }
                       adapter1 = new Frag1_ToDoListAdapter(getActivity(),list);
                       ourdoes.setAdapter(adapter1);
                       adapter1.notifyDataSetChanged();

                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure( @NonNull Exception e ) {
                       Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                   }
               });

    }
}