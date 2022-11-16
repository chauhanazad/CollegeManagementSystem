package com.example.college.notice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder>{

    List<Notice_Details> noticeDetailsList;
    Context c;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public NoticeAdapter(List<Notice_Details> noticeDetailsList, Context c) {
        this.noticeDetailsList = noticeDetailsList;
        this.c = c;
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.design_notice, parent,false);
        NoticeHolder holder=new NoticeHolder(v,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder holder, final int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            holder.t1.setText(noticeDetailsList.get(position).getTitle());
            holder.lin.setBackground(null);
            holder.lin.setBackgroundColor(Color.WHITE);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(c,ViewNoticeMsg.class);
                    i.putExtra("title",noticeDetailsList.get(position).getTitle());
                    i.putExtra("message",noticeDetailsList.get(position).getMessage());
                    c.startActivity(i);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        int shimmer_no=18;
        return showShimmer ? shimmer_no:noticeDetailsList.size();
    }


    public interface OnItemClickListener {

        void onViewClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class NoticeHolder extends RecyclerView.ViewHolder {
        TextView t1;
        LinearLayout lin;
        ShimmerFrameLayout shimmer;
        public NoticeHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            t1=itemView.findViewById(R.id.title_text);
            shimmer=itemView.findViewById(R.id.shimmer);
            lin=itemView.findViewById(R.id.lin);

            t1.setOnClickListener(new View.OnClickListener() {
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

    public void clear()
    {
        noticeDetailsList.clear();
    }
}
