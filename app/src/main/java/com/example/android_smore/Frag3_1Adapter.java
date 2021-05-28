package com.example.android_smore;

/**
 * 챌린지 추가_세부 목록 추가
 */

import android.app.AlertDialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_smore.Model.Frag3DataModel;

import java.util.ArrayList;
import java.util.List;

public class Frag3_1Adapter extends RecyclerView.Adapter<Frag3_1Adapter.Frag3_1ViewHolder>{


    private ArrayList<Frag3DataModel> mList;
    private Context mContext;

    public class Frag3_1ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView list;
        protected ImageButton button;

        public Frag3_1ViewHolder(View view) {
            super(view);
            this.list = view.findViewById(R.id.list_item);
            this.button = view.findViewById(R.id.list_button);

            view.setOnClickListener(this);
            button.setOnClickListener(this);

        }




        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.list_button:
                    mList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), mList.size());
                    break;
            }


        }
    }

    public Frag3_1Adapter(Context context,ArrayList<Frag3DataModel> list){
        this.mList = list;
        mContext = context;
    }


    @NonNull
    @Override
    public Frag3_1Adapter.Frag3_1ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_1,viewGroup,false);

        Frag3_1ViewHolder viewHolder = new Frag3_1ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Frag3_1Adapter.Frag3_1ViewHolder viewHolder, int position) {
        viewHolder.list.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        viewHolder.list.setText(mList.get(position).getList());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public List<Frag3DataModel> getItemList() {
        return this.mList;
    }
}