package com.example.college.event;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.college.IPActivity;
import com.example.college.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.EventHolder>{

    Context c;
    List<Events> eventList;
    OnItemClickListener mListener;
    boolean showShimmer=true;
    public eventAdapter(Context c, List<Events> eventList) {
        this.c = c;
        this.eventList = eventList;
    }
    @NonNull
    @Override
    public eventAdapter.EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.design_event, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_event, parent, false );

        eventAdapter.EventHolder holder = new eventAdapter.EventHolder(v,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull eventAdapter.EventHolder holder, final int position) {
        if(showShimmer)
        {
            holder.shimmer.startShimmer();
        }
        else
        {
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);
            holder.EventName.setText(eventList.get(position).getName());
            holder.t1.setText("PDF");
            holder.t1.setBackground(null);
            holder.lin.setBackground(null);
            holder.t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte = new Intent(Intent.ACTION_VIEW);
                    inte.setDataAndType(
                            Uri.parse("http://"+ IPActivity.ip+"/MLP/"+eventList.get(position).getfile_path()),
                            "application/pdf");
                    c.startActivity(inte);
                }
            });
        }

    }

    @Override
    public int getItemCount() {

        int shimmer_no=14;
        return showShimmer ? shimmer_no:eventList.size();
    }

    public interface OnItemClickListener {

        void onViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class EventHolder extends RecyclerView.ViewHolder {
        TextView EventName,t1;
        CardView cardView,super_card;
        ShimmerFrameLayout shimmer;
        LinearLayout lin;

        public EventHolder(@NonNull View itemView,  final OnItemClickListener listener) {
            super(itemView);
            EventName=itemView.findViewById(R.id.event_name);
            cardView=itemView.findViewById(R.id.path1);
            super_card=itemView.findViewById(R.id.super_card);
            t1=itemView.findViewById(R.id.t1);
            shimmer=itemView.findViewById(R.id.shimmer);
            lin=itemView.findViewById(R.id.lin);
            cardView.setOnClickListener(new View.OnClickListener() {
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
        eventList.clear();
    }
}
