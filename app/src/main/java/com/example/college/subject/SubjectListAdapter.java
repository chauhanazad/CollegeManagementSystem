package com.example.college.subject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.college.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectListHolder>{

    List<Subjects> subjectsList;
    Context c;
    OnItemClickListener mListener;

    public SubjectListAdapter(List<Subjects> subjectsList, Context c) {
        this.subjectsList = subjectsList;
        this.c = c;
    }

    @NonNull
    @Override
    public SubjectListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in=LayoutInflater.from(parent.getContext());
        View v= in.inflate(R.layout.design_subject_list,parent,false);
        SubjectListHolder holder=new SubjectListHolder(v,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectListHolder holder, int position) {
        holder.id.setText(subjectsList.get(position).getSub_id());
        holder.name.setText(subjectsList.get(position).getSub_name());
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public interface OnItemClickListener {
//        void onDeleteClick(int position);
        void onUpdateClick(int position);
//        void onViewClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class SubjectListHolder extends RecyclerView.ViewHolder
    {
        TextView id,name;

        public SubjectListHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            id=itemView.findViewById(R.id.sub_id);
            name=itemView.findViewById(R.id.sub_name);

        }
    }
}
