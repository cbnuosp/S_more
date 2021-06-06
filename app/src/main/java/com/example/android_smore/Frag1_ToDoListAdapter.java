package com.example.android_smore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_smore.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class Frag1_ToDoListAdapter extends RecyclerView.Adapter<Frag1_ToDoListAdapter.MyViewHolder>{
    private Context mContext;
    ArrayList<ToDoModel> myDoes;

    public String idUpdate = ""; //업데이트 해야하는 item의 id

    public Frag1_ToDoListAdapter(Context mContext, ArrayList<ToDoModel> list){
        this.myDoes=list;
        this.mContext=mContext;
    }



    @NonNull
    @Override
    //viewholder 생성
    //레이아웃을 만들어서 Holder에 저장/뷰홀더를 생성하고 뷰를 붙여주는 부분
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int viewType ) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag1_4,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(view);
        return new MyViewHolder(view);
    }


    //넘겨받은 데이터를 화면에 출력하는 역할
    //재활용 되는 뷰가 호출하여 실행되는 메소드
    //뷰 홀더를 전달하고 어댑터는 position의 데이터를 결함
   @Override
    public void onBindViewHolder( @NonNull Frag1_ToDoListAdapter.MyViewHolder holder, final int position ) {
       holder.titledoes.setText(myDoes.get(position).getTitledoes());
       holder.datedoes.setText(myDoes.get(position).getDatedoes());
       holder.descdoes.setText(myDoes.get(position).getDescdoes());


        final String titledoes = myDoes.get(position).getTitledoes();
        final String datedoes = myDoes.get(position).getDatedoes();
        final String descdoes = myDoes.get(position).getDescdoes();
        final String keydoes = myDoes.get(position).getKeydoes();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick( View view ) {
//                Frag1_1.titledoes.setText(list.get(p));
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mContext = v.getContext();
//                Intent aa = new Intent(mContext,Frag1_3.class);
//                aa.setFlags(aa.FLAG_ACTIVITY_NEW_TASK);
//                aa.putExtra("titledoes", myDoes.get(position).getTitledoes());
//                aa.putExtra("datedoes", myDoes.get(position).getDatedoes());
//                aa.putExtra("descdoes", myDoes.get(position).getDescdoes());
////                aa.putExtra("keydoes", myDoes.get(position).getKeydoes());
//                mContext.startActivity(aa);
//                mContext.startActivity(aa);

                Frag1_3 fragment = new Frag1_3();
                Bundle bundle =new Bundle();
                bundle.putString("titledoes",titledoes);
                bundle.putString("datedoes",datedoes);
                bundle.putString("descdoes",descdoes);
                bundle.putString("keydoes",keydoes);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();



            }
        });
    }


    //viewholder가 하나의 view를 보존하는 역할
    class MyViewHolder extends RecyclerView.ViewHolder{

        public View mView;
        TextView titledoes, descdoes, datedoes;

        public MyViewHolder( @NonNull View itemView){
            super(itemView);
            mView=itemView;
            titledoes = (TextView)itemView.findViewById(R.id.titledoes);
            datedoes = (TextView)itemView.findViewById(R.id.datedoes);
            descdoes = (TextView)itemView.findViewById(R.id.descdoes);



        }

    }
    public int getItemCount() {
        if(myDoes.isEmpty()) {
            return 0;
        }
        else{
            return myDoes.size();
        }
    }

    public List<ToDoModel> getItemList(){
        return this.myDoes;
    }
}