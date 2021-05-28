package com.example.android_smore.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 챌린지 메인화면 목록에 대한 패키지
 */

public abstract class BaseViewHolder<B extends ViewDataBinding, D> extends RecyclerView.ViewHolder {
    protected B binding;
    private Context context;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutResId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
        this.binding = DataBindingUtil.bind(itemView);
        context = itemView.getContext();
    }

    protected abstract void bind(int index,D data);

    protected void recycled() {
        // no-op
    }

    protected Context getContext() {
        return context;
    }
}