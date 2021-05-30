package com.example.android_smore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.example.android_smore.L;
import com.example.android_smore.Model.ChallengeResponse;
import com.example.android_smore.R;
import com.example.android_smore.base.BaseRecyclerAdapter;
import com.example.android_smore.base.BaseViewHolder;
import com.example.android_smore.databinding.ItemPercetChallengeRowBinding;

/**
 * 챌린지 목록 프로그래스바 출력_adapter
 */

public class ChallengeAdapter extends BaseRecyclerAdapter<ChallengeResponse, ChallengeAdapter.ViewHolder> {

    public ChallengeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<ItemPercetChallengeRowBinding, ChallengeResponse> {

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_percet_challenge_row);
        }

        @Override
        protected void bind(int position, ChallengeResponse data) {
            L.e("::::::data " + data);

            float percent = 0f;
            if (data.getChallengeModel().getList() != null && data.getChallengeModel().getList().size() > 0) {
                float total = data.getChallengeModel().getList().size();
                float archiveCount = 0f;
                for (int i = 0; i < data.getChallengeModel().getList().size(); i++) {
                    boolean isArchive = data.getChallengeModel().getList().get(i).isCheck();
                    if (isArchive) {
                        archiveCount += 1;
                    }
                }
                L.i("::::archiveCount " + archiveCount + " total " + total);
                percent = archiveCount / total;//체크된 개수/전체 개수
            }
            //프로그래스바 퍼센트
            L.i("::::percent " + percent);
            binding.progress.setShowingPercentage(false);
            binding.progress.setMaximumPercentage(percent);
            //프로그래스바 색상
            binding.progress.setProgressColor(Color.parseColor("#3CB371"));
            binding.progress.setProgressBackgroundColor(Color.parseColor("#f5f5f5"));
            //프로그래스바 타이틀
            binding.tvName.setText(data.getChallengeModel().getTitle());
            binding.tvPercent.setText(Math.round(percent*100) + "%");//반올림

        }
    }
}
