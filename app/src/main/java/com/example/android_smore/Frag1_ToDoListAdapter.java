package com.example.android_smore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_smore.Model.ToDoModel;

import java.util.ArrayList;

public class Frag1_ToDoListAdapter extends RecyclerView.Adapter<Frag1_ToDoListAdapter.MyViewHolder>{
    Context context;
    ArrayList<ToDoModel> myDoes;

    public String idUpdate = ""; //업데이트 해야하는 item의 id

    public Frag1_ToDoListAdapter(Context c, ArrayList<ToDoModel> list){
        context=c;
        myDoes=list;
    }

    @NonNull
    @Override
    //viewholder 생성
    //row layout을 화면에 뿌려주고 holder에 연결
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int i ) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.frag1_4,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    //ViewHoder에 data 삽입
   @Override
    public void onBindViewHolder( @NonNull Frag1_ToDoListAdapter.MyViewHolder holder, int i ) {
       holder.titledoes.setText(myDoes.get(i).getTitledoes());
       holder.datedoes.setText(myDoes.get(i).getDatedoes());
       holder.descdoes.setText(myDoes.get(i).getDescdoes());


        final String getTitleDoes = myDoes.get(i).getTitledoes();
        final String getDescDoes = myDoes.get(i).getDescdoes();
        final String getDateDoes = myDoes.get(i).getDatedoes();
        final String getKeyDoes = myDoes.get(i).getKeydoes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,Frag1_3.class);
                aa.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                aa.putExtra("titledoes", getTitleDoes);
                aa.putExtra("datedoes", getDateDoes);
                aa.putExtra("descdoes", getDescDoes);
                aa.putExtra("keydoes", getKeyDoes);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(myDoes.isEmpty()) {
            return 0;
        }
        else{
            return myDoes.size();
        }
    }

    //viewholder가 하나의 view를 보존하는 역할
    class MyViewHolder extends RecyclerView.ViewHolder{
         TextView titledoes, descdoes, datedoes;

        public MyViewHolder( @NonNull View itemView){
            super(itemView);
            titledoes = (TextView)itemView.findViewById(R.id.titledoes);
            descdoes = (TextView)itemView.findViewById(R.id.descdoes);
            datedoes = (TextView)itemView.findViewById(R.id.datedoes);
        }
    }
}