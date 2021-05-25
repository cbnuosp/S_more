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

public class Adapter1 extends RecyclerView.Adapter<Adapter1.MyViewHolder>{

    private Context context;
    private ArrayList<Frag1_3> myDoes;

    public Adapter1(Context c,ArrayList<Frag1_3> p){
        this.context=c;
        this.myDoes=p;
    }

    @NonNull
    @Override
    //viewholder 생성
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.frag1_3,viewGroup,false));
    }

    @Override
    public void onBindViewHolder( @NonNull MyViewHolder myViewHolder, int i ) {
        myViewHolder.titledoes.setText(myDoes.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myDoes.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myDoes.get(i).getDatedoes());

        final String getTitleDoes = myDoes.get(i).getTitledoes();
        final String getDescDoes = myDoes.get(i).getDescdoes();
        final String getDateDoes = myDoes.get(i).getDatedoes();

//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            public void onClick( View v ) {
//                Intent aa=new Intent(context, EditTaskDesk.class);
//                aa.putExtra("titledoes",getTitleDoes);
//                aa.putExtra("descdoes", getDescDoes);
//                aa.putExtra("datedoes", getDateDoes);
//                context.startActivity(aa);
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return myDoes.size();
    }
    //viewholder가 하나의 view를 보존하는 역할
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titledoes, descdoes, datedoes;
        public MyViewHolder( @NonNull View itemView){
            super(itemView);
            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            descdoes = (TextView)itemView.findViewById(R.id.descdoes);
            datedoes =(TextView)itemView.findViewById(R.id.datedoes);
        }
    }
}