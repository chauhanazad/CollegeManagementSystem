package com.example.college.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.college.LoadingDialog;
import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewNotice extends AppCompatActivity {

    RecyclerView rv;
    SwipeRefreshLayout sr1;
    List<Notice_Details> noticeDetailsList;
    List<Notice_Details> noticelist;
    NoticeAdapter adapter;
    TextView t1;
    int n=0;
//    LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notice");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        dialog=new LoadingDialog(this);
        t1=findViewById(R.id.info);
        noticeDetailsList=new ArrayList<>();
        noticelist=new ArrayList<>();
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        sr1=findViewById(R.id.swiperefresh);

        adapter=new NoticeAdapter(noticelist, ViewNotice.this);
        rv.setAdapter(adapter);
        sr1.setEnabled(false);
        updateOperation();
        sr1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(n==1)
                {
                    adapter.clear();
                }
                updateOperation();
                sr1.setRefreshing(false);
            }
        });
    }

    void updateOperation()
    {
        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);

        Call<List<Notice_Details>> call=api.viewNotice();
        call.enqueue(new Callback<List<Notice_Details>>() {
            @Override
            public void onResponse(Call<List<Notice_Details>> call, Response<List<Notice_Details>> response) {
                noticeDetailsList=response.body();
                if(noticeDetailsList.size()>0 && noticeDetailsList.get(0).getResponse().equals(""))
                {
                    t1.setVisibility(View.GONE);
//                    Toast.makeText(ViewNotice.this, ""+noticeDetailsList.size(), Toast.LENGTH_SHORT).show();
                    for(Notice_Details d:noticeDetailsList)
                    {
                        noticelist.add(new Notice_Details(d.getNotice_id(),d.getTitle(),d.getMessage()));
                    }
                    n=1;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.showShimmer=false;
                            adapter.notifyDataSetChanged();
                            sr1.setEnabled(true);

                        }
                    },3000);
                    adapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {

                        @Override
                        public void onViewClick(int position) {
                            Intent i=new Intent(ViewNotice.this,ViewNoticeMsg.class);
                            i.putExtra("title",noticelist.get(position).getTitle());
                            i.putExtra("message",noticelist.get(position).getMessage());
                            startActivity(i);
                        }
                    });
                }
                else
                {
                    t1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Notice_Details>> call, Throwable t) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                snacktv.setTextColor(getResources().getColor(R.color.white));
                snackbar.show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}