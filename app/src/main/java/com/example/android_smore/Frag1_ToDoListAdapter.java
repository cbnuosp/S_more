package com.example.android_smore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Frag1_ToDoListAdapter extends RecyclerView.Adapter<Frag1_ToDoListAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Frag1_3> myDoes;

    public Frag1_ToDoListAdapter( Context c, ArrayList<Frag1_3> list){
        this.context=c;
        this.myDoes=list;
    }

    @NonNull
    @Override
    //viewholder 생성
    //row layout을 화면에 뿌려주고 holder에 연결
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.frag1_3,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    //ViewHoder에 data 삽입입
   @Override
    public void onBindViewHolder( @NonNull Frag1_ToDoListAdapter.MyViewHolder holder, int i ) {

        final String getTitleDoes = myDoes.get(i).getTitledoes();
        final String getDescDoes = myDoes.get(i).getDescdoes();
        final String getDateDoes = myDoes.get(i).getDatedoes();
        final String getKeyDoes = myDoes.get(i).getKeydoes();

        holder.titledoes.setText(myDoes.get(i).getTitledoes());
        holder.descdoes.setText(myDoes.get(i).getDescdoes());
        holder.datedoes.setText(myDoes.get(i).getDatedoes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,Frag1_3.class);
                aa.putExtra("titledoes", getTitleDoes);
                aa.putExtra("descdoes", getDescDoes);
                aa.putExtra("datedoes", getDateDoes);
                aa.putExtra("keydoes", getKeyDoes);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    //viewholder가 하나의 view를 보존하는 역할
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titledoes, descdoes, datedoes;

        public MyViewHolder( @NonNull View itemView){
            super(itemView);
            titledoes = (TextView)itemView.findViewById(R.id.titledoes);
            descdoes = (TextView)itemView.findViewById(R.id.descdoes);
            datedoes =(TextView)itemView.findViewById(R.id.datedoes);
        }
    }
}