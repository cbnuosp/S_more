package com.example.android_smore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_smore.Model.ChallengeModel;
import com.example.android_smore.Model.ChallengeResponse;
import com.example.android_smore.adapter.ChallengeAdapter;
import com.example.android_smore.base.BaseFragment;
import com.example.android_smore.base.OnItemClickListener;
import com.example.android_smore.databinding.Frag3Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Frag3 extends BaseFragment<Frag3Binding> {
    private ChallengeAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected int layoutRes() {
        return R.layout.frag3;
    }

    @Override
    protected void onViewCreated() {
        initRecyclerView();
        onLoad();
        binding.addCList.setOnClickListener(view -> getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag3_1()).commit());
    }

    //uid로 쿼리 데이터 로드
    private void onLoad() {
        Query challengeReq = db.collection("Challenges").whereEqualTo("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
        challengeReq.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                L.e(":::::::::::::::::::isSuccessful  " + task.isSuccessful());
                if (task.isSuccessful()) {
                    if (task.getResult() == null) {
                        return;
                    }
                    List<ChallengeResponse> response = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        L.d(document.getId() + " => " + document.getData());
                        ChallengeModel model = document.toObject(ChallengeModel.class);
                        response.add(new ChallengeResponse(document.getId(), model));
                    }
                    adapter.updateItems(response);

                } else {
                    L.e("Error getting documents: " + task.getException());
                }
            }
        });


    }

    private void initRecyclerView() {
        adapter = new ChallengeAdapter(getActivity());
        binding.rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvContent.setHasFixedSize(true);
        binding.rvContent.setAdapter(adapter);


        adapter.setOnItemClickListener(position -> {
            ChallengeResponse item = adapter.getItem(position);
            getFragmentManager().beginTransaction().replace(R.id.main_frame, Frag3_2.newInstance(item.getId())).commit();
        });
    }

}