package com.example.college.onlineexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class ViewOnlineExamList extends AppCompatActivity {

    RecyclerView rv;
    TextView t1;
    SwipeRefreshLayout sr1;
    Retrofit retrofit;
    APIclient api;

    List<Exam_Detail> examList;
    List<Exam_Detail> examdata;
    ViewOnlineExamAdapter adapter;
//    LoadingDialog dialog;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_online_exam_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Online Exam");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        examdata=new ArrayList<>();
        examList=new ArrayList<>();
//        dialog=new LoadingDialog(this);
        rv=findViewById(R.id.rv_online_exam);
        rv.setLayoutManager(new LinearLayoutManager(this));
        t1=findViewById(R.id.info);
        sr1=findViewById(R.id.swiperefresh);

        adapter=new ViewOnlineExamAdapter(ViewOnlineExamList.this,examdata);
        rv.setAdapter(adapter);
        sr1.setEnabled(false);

        myupdateoperation();

        sr1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(n==1) {
                    adapter.clear();
                }
                myupdateoperation();
                sr1.setRefreshing(false);
            }
        });
    }

    void myupdateoperation()
    {
//        dialog.startAlertDialog();
        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "no");

        retrofit= MyRetrofit.getRetrofit();
        api=retrofit.create(APIclient.class);
        Call<List<Exam_Detail>> call=api.getExamsForStudent(email);
        call.enqueue(new Callback<List<Exam_Detail>>() {
            @Override
            public void onResponse(Call<List<Exam_Detail>> call, Response<List<Exam_Detail>> response) {
                examList=response.body();
                if(examList.size()>0)
                {
//                    dialog.dismissDialog();
                    t1.setVisibility(View.GONE);
                    n=1;
//                    Toast.makeText(ViewOnlineExamList.this, "hii", Toast.LENGTH_SHORT).show();
                    for(Exam_Detail e:examList)
                    {
                        examdata.add(new Exam_Detail(e.getExam_id(),e.getExam_name(),e.getSub_id(),e.getSub_name(),e.getDate_added()));
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.showShimmer=false;
                            adapter.notifyDataSetChanged();
                            sr1.setEnabled(true);
                        }
                    },3000);
                    adapter.setOnItemClickListener(new ViewOnlineExamAdapter.OnItemClickListener() {
                        @Override
                        public void onViewClick(final int position) {

                        }
                    });
                }
                else
                {
//                    dialog.dismissDialog();
                    t1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Exam_Detail>> call, Throwable t) {
//                dialog.dismissDialog();
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
        return true;
    }

}