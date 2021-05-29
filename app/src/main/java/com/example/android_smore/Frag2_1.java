package com.example.android_smore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class Frag2_1 extends Fragment {
    private TextView monday[] = new TextView[10];
    private TextView tuesday[] = new TextView[10];
    private TextView wednesday[] = new TextView[10];
    private TextView thursday[] = new TextView[10];
    private TextView friday[] = new TextView[10];

    private Frag2_Schedule schedule = new Frag2_Schedule();
    private String timetableid;

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        monday[1]=(TextView) getView().findViewById(R.id.monday1);
        monday[2]=(TextView) getView().findViewById(R.id.monday2);
        monday[3]=(TextView) getView().findViewById(R.id.monday3);
        monday[4]=(TextView) getView().findViewById(R.id.monday4);
        monday[5]=(TextView) getView().findViewById(R.id.monday5);
        monday[6]=(TextView) getView().findViewById(R.id.monday6);
        monday[7]=(TextView) getView().findViewById(R.id.monday7);
        monday[8]=(TextView) getView().findViewById(R.id.monday8);
        monday[9]=(TextView) getView().findViewById(R.id.monday9);

        tuesday[1]=(TextView) getView().findViewById(R.id.tuesday1);
        tuesday[2]=(TextView) getView().findViewById(R.id.tuesday2);
        tuesday[3]=(TextView) getView().findViewById(R.id.tuesday3);
        tuesday[4]=(TextView) getView().findViewById(R.id.tuesday4);
        tuesday[5]=(TextView) getView().findViewById(R.id.tuesday5);
        tuesday[6]=(TextView) getView().findViewById(R.id.tuesday6);
        tuesday[7]=(TextView) getView().findViewById(R.id.tuesday7);
        tuesday[8]=(TextView) getView().findViewById(R.id.tuesday8);
        tuesday[9]=(TextView) getView().findViewById(R.id.tuesday9);

        wednesday[1]=(TextView) getView().findViewById(R.id.wednesday1);
        wednesday[2]=(TextView) getView().findViewById(R.id.wednesday2);
        wednesday[3]=(TextView) getView().findViewById(R.id.wednesday3);
        wednesday[4]=(TextView) getView().findViewById(R.id.wednesday4);
        wednesday[5]=(TextView) getView().findViewById(R.id.wednesday5);
        wednesday[6]=(TextView) getView().findViewById(R.id.wednesday6);
        wednesday[7]=(TextView) getView().findViewById(R.id.wednesday7);
        wednesday[8]=(TextView) getView().findViewById(R.id.wednesday8);
        wednesday[9]=(TextView) getView().findViewById(R.id.wednesday9);

        thursday[1]=(TextView) getView().findViewById(R.id.thursday1);
        thursday[2]=(TextView) getView().findViewById(R.id.thursday2);
        thursday[3]=(TextView) getView().findViewById(R.id.thursday3);
        thursday[4]=(TextView) getView().findViewById(R.id.thursday4);
        thursday[5]=(TextView) getView().findViewById(R.id.thursday5);
        thursday[6]=(TextView) getView().findViewById(R.id.thursday6);
        thursday[7]=(TextView) getView().findViewById(R.id.thursday7);
        thursday[8]=(TextView) getView().findViewById(R.id.thursday8);
        thursday[9]=(TextView) getView().findViewById(R.id.thursday9);

        friday[1]=(TextView) getView().findViewById(R.id.friday1);
        friday[2]=(TextView) getView().findViewById(R.id.friday2);
        friday[3]=(TextView) getView().findViewById(R.id.friday3);
        friday[4]=(TextView) getView().findViewById(R.id.friday4);
        friday[5]=(TextView) getView().findViewById(R.id.friday5);
        friday[6]=(TextView) getView().findViewById(R.id.friday6);
        friday[7]=(TextView) getView().findViewById(R.id.friday7);
        friday[8]=(TextView) getView().findViewById(R.id.friday8);
        friday[9]=(TextView) getView().findViewById(R.id.friday9);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag2_1, container, false);

        // Firebase code
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getEmail();

        final TextView timetablenametxt;
        timetablenametxt = (TextView) v.findViewById(R.id.timetablename_text);



        // 시간표 이름 변경
        db.collection("Timetable")
                .whereEqualTo("select",true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                if(document.get("id").toString().equals(uid)){  // 로그인된 아이디일때
                                    timetablenametxt.setText(document.get("tablename").toString());
                                }
                            }
                        }
                        else{
                            Log.d(TAG,"Error getting documents : ",task.getException());
                        }
                    }
                });


        return v;
    }
}
