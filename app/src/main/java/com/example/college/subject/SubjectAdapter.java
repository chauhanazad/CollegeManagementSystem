package com.example.college.subject;

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

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder>{

    List<String> a;
    Context c;

    public SubjectAdapter(List<String> a, Context c) {
        this.a = a;
        this.c = c;
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in=LayoutInflater.from(parent.getContext());
        View v= in.inflate(R.layout.mydesign,null);
        SubjectHolder holder=new SubjectHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        holder.t1.setText(a.get(position));
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t=(TextView) v;
                String a=t.getText().toString();
                Intent i=new Intent(c,SubjectList.class);
                i.putExtra("sem_name",a);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return a.size();
    }

    class SubjectHolder extends RecyclerView.ViewHolder
    {
        TextView t1;

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
        }
    }
}
