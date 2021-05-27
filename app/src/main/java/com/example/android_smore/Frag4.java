package com.example.android_smore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Frag4 extends Fragment {
    private View view;
    private TextView idtxt;
    private TextView logout;
    String idtext;
    FirebaseFirestore db;
    private DocumentReference docRef;
    private static final String TAG = "DocSnippets";
    //Firebase로 로그인한 사용자 정보 알기 위해
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view= (ViewGroup)inflater.inflate(R.layout.frag4, container, false);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        idtxt = (TextView)view.findViewById(R.id.userid);
        logout = (TextView)view.findViewById(R.id.logoutbtn);
        idtext = user.getEmail();
        idtxt.setText(idtext);

        Button pageButton = (Button)view.findViewById(R.id.pageButton);
        pageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), Frag4_1.class);
                startActivity(mIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent lIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(lIntent);
            }
        });

        return view;
    }


}
