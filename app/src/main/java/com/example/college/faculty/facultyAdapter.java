package com.example.college.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.college.IPActivity;
import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;


import java.util.List;

public class facultyAdapter extends RecyclerView.Adapter<facultyAdapter.facultyHolder> {
    Context c;
    List<FacultyItem> facultyList;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public facultyAdapter(Context c, List<FacultyItem> facultyList) {
        this.c = c;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public facultyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.design_faculty, null);
        facultyHolder holder = new facultyHolder(v,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull facultyHolder holder, int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else
        {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            holder.facultyName.setText(facultyList.get(position).getFirstName()+" "+facultyList.get(position).getMiddleName()+" "+facultyList.get(position).getLastName());
            holder.facultyName.setBackground(null);
            holder.facultyEmail.setText(facultyList.get(position).getEmail());
            holder.facultyEmail.setBackground(null);
            Glide.with(c)
                    .load("http://"+ IPActivity.ip +"/MLP/"+ facultyList.get(position).getImagePath())
                    .into(holder.facultyImage);
            holder.facultyImage.setBackground(null);
        }

    }

    @Override
    public int getItemCount() {

        int shimmer_no=9;
        return showShimmer ? shimmer_no:facultyList.size();
    }

    public interface OnItemClickListener{
        void onDeleteClick(int position);
        void onUpdateClick(int position);
        void onViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    class facultyHolder extends RecyclerView.ViewHolder {
        ShimmerFrameLayout shimmer;
        TextView facultyName, facultyEmail;
        ImageView facultyImage;

        public facultyHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            shimmer=itemView.findViewById(R.id.shimmer);
            facultyImage = itemView.findViewById(R.id.faculty_image);
            facultyName = itemView.findViewById(R.id.faculty_name);
            facultyEmail = itemView.findViewById(R.id.faculty_email);



        }
    }

    public void clear()
    {
        facultyList.clear();
    }
}
