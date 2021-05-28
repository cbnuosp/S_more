package com.example.android_smore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.android_smore.L;
import com.example.android_smore.Model.ChallengeResponse;
import com.example.android_smore.Model.ChallengeTaskModel;
import com.example.android_smore.R;
import com.example.android_smore.base.BaseRecyclerAdapter;
import com.example.android_smore.base.BaseViewHolder;
import com.example.android_smore.databinding.ItemPercetChallengeRowBinding;
import com.example.android_smore.databinding.ItemTaskUpdateChallengeRowBinding;

/**
 * 챌린지 달성 목록들 체크 후 저장 -> 자동 갱신
 */
public abstract class ChallengeUpdateAdapter extends BaseRecyclerAdapter<ChallengeTaskModel, ChallengeUpdateAdapter.ViewHolder> {

    public abstract void onCheckBoxClick(int position, ChallengeTaskModel data);

    public ChallengeUpdateAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<ItemTaskUpdateChallengeRowBinding, ChallengeTaskModel> {

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_task_update_challenge_row);
        }

        @Override
        protected void bind(int position, ChallengeTaskModel data) {
            binding.tvName.setText(data.getTitle());
            binding.ivCheck.setChecked(data.isCheck());

            binding.ivCheck.setOnClickListener(view -> {
                data.setCheck(binding.ivCheck.isChecked());
                onCheckBoxClick(position, data);
            });

        }
    }
}
