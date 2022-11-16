package com.example.college.setting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.college.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    List<String> a;
    Context c;

    public MyAdapter(List<String> a, Context c) {
        this.a=a;
        this.c=c;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in=LayoutInflater.from(parent.getContext());
        View v= in.inflate(R.layout.mydesign,null);
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.t1.setText(""+a.get(position));
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t=(TextView) v;
                String a=t.getText().toString();
                if(a.equals("Change Password"))
                {
                    Intent i=new Intent(c,ChangePassword.class);
                    c.startActivity(i);
                }
                else if(a.equals("Change Email"))
                {
                    Intent i=new Intent(c,ChangeEmail.class);
                    c.startActivity(i);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return a.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView t1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
        }
    }
}
