package com.example.college.assignment;

import android.content.Context;
import android.content.Intent;
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

public class assignmentAdapter extends RecyclerView.Adapter<assignmentAdapter.assignmentHolder>{

    Context c;
    List<assignment> assignmentList;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public assignmentAdapter(Context c, List<assignment> assignmentList) {
        this.c = c;
        this.assignmentList = assignmentList;
    }

    @NonNull
    @Override
    public assignmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.design_assignment, parent, false);
        assignmentHolder holder = new assignmentHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull assignmentHolder holder, final int position) {
            if(showShimmer)
            {
                holder.shimmer.startShimmer();
            }
            else {
                holder.shimmer.stopShimmer();
                holder.shimmer.setShimmer(null);
                holder.assignmentSub.setText(assignmentList.get(position).getSub_name());
                holder.assignmentSub.setBackground(null);
                holder.assignment_sem.setText(assignmentList.get(position).getSem_name());
                holder.assignment_sem.setBackground(null);

                holder.pdf_text.setBackground(null);
                holder.pdf_text.setText("PDF");

                holder.assignmentCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent inte = new Intent(Intent.ACTION_VIEW);
                        inte.setDataAndType(
                                Uri.parse("http://"+ IPActivity.ip+"/MLP/"+assignmentList.get(position).getImage_path()),
                                "application/pdf");
                        c.startActivity(inte);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        int shimmer_no=10;
        return showShimmer ? shimmer_no:assignmentList.size();
    }

    public interface OnItemClickListener {
        void onViewClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class assignmentHolder extends RecyclerView.ViewHolder {
        TextView assignmentSub,assignment_sem,pdf_text;
        CardView assignmentCard;
        ShimmerFrameLayout shimmer;

        public assignmentHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            assignmentSub = itemView.findViewById(R.id.assignment_sub);
            assignment_sem=itemView.findViewById(R.id.assignment_sem);
            assignmentCard = itemView.findViewById(R.id.pdfpath);
            shimmer=itemView.findViewById(R.id.shimmer);
            pdf_text=itemView.findViewById(R.id.pdf_text);
            assignmentCard.setOnClickListener(new View.OnClickListener() {
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
    void clear()
    {
        assignmentList.clear();
    }
}
