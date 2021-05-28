package com.example.android_smore;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_smore.Model.ChallengeModel;
import com.example.android_smore.Model.ChallengeResponse;
import com.example.android_smore.Model.ChallengeTaskModel;
import com.example.android_smore.adapter.ChallengeAdapter;
import com.example.android_smore.adapter.ChallengeUpdateAdapter;
import com.example.android_smore.base.BaseFragment;
import com.example.android_smore.databinding.Frag32Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;



public class Frag3_2 extends BaseFragment<Frag32Binding> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference req;
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private ChallengeUpdateAdapter adapter;
    private ChallengeModel currentItem;


    private View view;

    public Frag3_2() {
        // Required empty public constructor
    }

    public static Frag3_2 newInstance(String param1) {
        Frag3_2 fragment = new Frag3_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutRes() {
        return R.layout.frag3_2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            L.i("::: ID " + mParam1);
        }
    }

    //체크 여부 저장
    @Override
    protected void onViewCreated() {
        req = db.collection("Challenges").document(mParam1);
        initRecyclerView();
        onLoad();
        binding.quitDetails.setOnClickListener(view -> onFinish());
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentItem.setList(adapter.getItemList());
                WriteBatch batch = db.batch();
                batch.set(req, currentItem);
                batch.commit().addOnCompleteListener(task -> {
                    L.e("::::::::::::::task " + task.isSuccessful());
                    if (task.isSuccessful()) {
                        onFinish();
                        Toast.makeText(getActivity(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //DB에서 데이터 삭제
        binding.btnDelete.setOnClickListener(view -> req.delete().addOnSuccessListener(aVoid -> {
            onFinish();
            Toast.makeText(getActivity(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show()));
    }

    //삭제 후 frag3으로 이동
    private void onFinish() {
        getFragmentManager().beginTransaction().replace(R.id.main_frame, new Frag3()).commit();
    }

    private void initRecyclerView() {
        adapter = new ChallengeUpdateAdapter(getActivity()) {
            @Override
            public void onCheckBoxClick(int position, ChallengeTaskModel data) {
                adapter.updateItem(position, data);
            }
        };
        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listView.setHasFixedSize(true);
        binding.listView.setAdapter(adapter);

    }


    //데이터 로드
    private void onLoad() {
        req.get().addOnCompleteListener(task -> {
            L.e(":::::::::::::::::::isSuccessful  " + task.isSuccessful());
            if (task.isSuccessful()) {
                if (task.getResult() == null) {
                    return;
                }
                DocumentSnapshot document = task.getResult();
                L.d(document.getId() + " => " + document.getData());
                String id = document.getId();
                ChallengeModel model = document.toObject(ChallengeModel.class);

                if (model == null) return;

                currentItem = model;
                binding.title.setText(model.getTitle());
                binding.SDate.setText(model.getStartDate());
                binding.EDate.setText(model.getEndDate());
                binding.detailsMemo.setText(model.getMemo());
                adapter.updateItems(model.getList());

            } else {
                L.e("Error getting documents: " + task.getException());
            }
        });
    }


}