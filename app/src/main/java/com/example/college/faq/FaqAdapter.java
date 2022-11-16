package com.example.college.faq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college.R;
import com.example.college.faculty.facultyAdapter;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqHolder>{

    Context c;
    List<Faq> faqList;
    OnItemClickListener mListener;
    public FaqAdapter(Context c, List<Faq> faqList) {
        this.c = c;
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FaqHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View v=inflater.inflate(R.layout.mydesign,parent,false);
        FaqHolder holder=new FaqHolder(v,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FaqHolder holder, final int position) {
        holder.t1.setText(faqList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
       return faqList.size();
    }

    public interface OnItemClickListener
    {
        void onViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
    class FaqHolder extends RecyclerView.ViewHolder
    {
        TextView t1;
        public FaqHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            t1=itemView.findViewById(R.id.t1);



            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                            listener.onViewClick(position);
                    }
                }
            });
        }
    }
}
