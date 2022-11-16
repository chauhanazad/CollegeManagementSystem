package com.example.college.oldpapers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.college.IPActivity;
import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class OldPaperAdapter extends  RecyclerView.Adapter<OldPaperAdapter.OldPaperHolder>{

    Context c;
    List<OldPaper> oldPaperList;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public OldPaperAdapter(Context c, List<OldPaper> oldPaperList) {
        this.c = c;
        this.oldPaperList = oldPaperList;
    }

    @NonNull
    @Override
    public OldPaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.design_assignment, parent, false);
        OldPaperHolder holder=new OldPaperHolder(view,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OldPaperHolder holder, final int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            holder.oldpaperyear.setText(oldPaperList.get(position).getYear());
            holder.oldpaperyear.setBackground(null);
            holder.oldpaperstatus.setText(oldPaperList.get(position).getStatus());
            holder.oldpaperstatus.setBackground(null);
            holder.t1.setText("PDF");
            holder.t1.setBackground(null);
            holder.oldpaperCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte = new Intent(Intent.ACTION_VIEW);
                    inte.setDataAndType(
                            Uri.parse("http://"+ IPActivity.ip+"/MLP/"+oldPaperList.get(position).getFile_path()),
                            "application/pdf");
                    c.startActivity(inte);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        int shimmer_no=10;
        return showShimmer ? shimmer_no:oldPaperList.size();
    }

    public interface OnItemClickListener {
        void onViewClick(int position);
//        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class OldPaperHolder extends RecyclerView.ViewHolder {
        TextView oldpaperyear,oldpaperstatus,t1;
        CardView oldpaperCard;
        ShimmerFrameLayout shimmer;

        public OldPaperHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            oldpaperstatus = itemView.findViewById(R.id.assignment_sub);
            oldpaperyear=itemView.findViewById(R.id.assignment_sem);
            oldpaperCard = itemView.findViewById(R.id.pdfpath);
            shimmer=itemView.findViewById(R.id.shimmer);
            t1=itemView.findViewById(R.id.pdf_text);
            oldpaperCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onViewClick(position);
                    }
                }
            });
        }
    }

    public void clear()
    {
        oldPaperList.clear();
    }
}
