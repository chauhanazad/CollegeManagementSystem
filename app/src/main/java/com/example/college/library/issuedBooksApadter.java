package com.example.college.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.college.IPActivity;
import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class issuedBooksApadter extends RecyclerView.Adapter<issuedBooksApadter.issuedBooksHolder> {
    Context c;
    List<Library> bookList;
    boolean showShimmer=true;
    public issuedBooksApadter(Context c, List<Library> bookList) {
        this.c = c;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public issuedBooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.design_view_issued_books, parent, false);
        issuedBooksHolder holder = new issuedBooksHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull issuedBooksHolder holder, final int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else
        {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            holder.subName.setText(bookList.get(position).getSub_name());

            holder.bookId.setText(""+bookList.get(position).getBook_id());

            holder.submitDate.setText(""+bookList.get(position).getIssue_date());

            int pos = position + 1;
            holder.srNo.setText(pos+")");
            holder.lin.setBackground(null);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, "Submission Date is "+bookList.get(position).getSubmission_date(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        int shimmer_no=17;
        return showShimmer ? shimmer_no:bookList.size();
    }

    class issuedBooksHolder extends RecyclerView.ViewHolder {
        TextView srNo, subName, bookId, submitDate;
        ShimmerFrameLayout shimmer;
        LinearLayout lin;

        public issuedBooksHolder(@NonNull View itemView) {
            super(itemView);
            srNo = itemView.findViewById(R.id.lib_man_sr_no);
            bookId = itemView.findViewById(R.id.lib_man_book_id);
            subName = itemView.findViewById(R.id.lib_man_sub_name);
            submitDate = itemView.findViewById(R.id.lib_man_submit_date);
            shimmer=itemView.findViewById(R.id.shimmer);
            lin=itemView.findViewById(R.id.lin);
        }
    }
}