package com.example.college.onlineexam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ViewOnlineExamAdapter extends RecyclerView.Adapter<ViewOnlineExamAdapter.examHolder>{

    Context c;
    List<Exam_Detail> examList;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public ViewOnlineExamAdapter(Context c, List<Exam_Detail> examList) {
        this.c = c;
        this.examList = examList;
    }

    @NonNull
    @Override
    public examHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.design_online_exam, parent, false);
        examHolder holder = new examHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull examHolder holder, final int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else
        {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            String date="";
            int pos = position + 1;
            holder.examSr.setText("" + pos+")");
            holder.examName.setText(examList.get(position).getExam_name());
            try {
                date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(examList.get(position).getDate_added()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
//        Log.d("my_errors", "Pos: " + pos + " Name: " + examList.get(position).getExamName() + " Sub: " + examList.get(position).getSubName() + " Date: " + examList.get(position).getDateAdded());
            holder.examSubName.setText(examList.get(position).getSub_name());
            holder.examDate.setText(date);
//            holder.cardView.setBackground(null);
            holder.lin.setBackground(null);
            holder.lin.setBackgroundColor(Color.WHITE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(c);
                    builder.setMessage("")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog1, int which) {
                                    Intent i=new Intent(c, TakingExam.class);
                                    i.putExtra("exam_id",examList.get(position).getExam_id());
                                    c.startActivity(i);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog1, int which) {
                                    dialog1.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Want to Give Exam");
                    alertDialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int shimmer_no=10;
        return showShimmer ? shimmer_no:examList.size();
    }

    public interface OnItemClickListener {
        void onViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class examHolder extends RecyclerView.ViewHolder {
        TextView examSr, examName, examSubName, examDate;
        ShimmerFrameLayout shimmer;
//        CardView cardView;
        LinearLayout lin;

        public examHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            examSr = itemView.findViewById(R.id.tv_online_exam_sr_);
            examName = itemView.findViewById(R.id.tv_online_exam_name);
            examSubName = itemView.findViewById(R.id.tv_online_exam_sub_name);
            examDate = itemView.findViewById(R.id.tv_online_exam_date_added);
            shimmer=itemView.findViewById(R.id.shimmer);
//            cardView=itemView.findViewById(R.id.cardview);
            lin=itemView.findViewById(R.id.lin);
            itemView.setOnClickListener(new View.OnClickListener() {
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
        examList.clear();
    }
}
