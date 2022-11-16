package com.example.college.oldpapers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.college.IPActivity;
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

public class ViewOldPaperList extends AppCompatActivity {

    RecyclerView rv;
    OldPaperAdapter adapter;
    List<OldPaper> oldPaperList;
    List<OldPaper> oldPapers;

    TextView t1;

    SwipeRefreshLayout srl;
    LoadingDialog dialog;
    int n=0;
    String sub_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_paper_list);

        sub_name=getIntent().getStringExtra("sub_name");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(sub_name);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        rv=findViewById(R.id.rv_oldpaper);
        rv.setLayoutManager(new LinearLayoutManager(this));
        t1=findViewById(R.id.oldinfo);
        srl=findViewById(R.id.swiperefresh);
        dialog=new LoadingDialog(this);

        oldPaperList=new ArrayList<>();
        oldPapers=new ArrayList<>();
        adapter = new OldPaperAdapter(ViewOldPaperList.this, oldPapers);
        rv.setAdapter(adapter);
        srl.setEnabled(false);
        updateOperation();

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(n==1)
                {
                    adapter.clear();
                }
                updateOperation();
                srl.setRefreshing(false);
            }
        });

    }

    void updateOperation()
    {
        dialog.startAlertDialog();
        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);
        Call<List<OldPaper>> call=api.getOldPapers(sub_name);
        call.enqueue(new Callback<List<OldPaper>>() {
            @Override
            public void onResponse(Call<List<OldPaper>> call, Response<List<OldPaper>> response) {
                oldPaperList=response.body();
                if(oldPaperList.size()>0)
                {
                    dialog.dismissDialog();
                    if(oldPaperList.get(0).getResponse().equals("")) {
//                        dialog.dismissDialog();
                        n=1;
                        t1.setVisibility(View.GONE);
                        for (OldPaper p : oldPaperList) {
                            oldPapers.add(new OldPaper(p.getOpid(),p.getFile_path(), p.getYear(), p.getStatus()));
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.showShimmer=false;
                                adapter.notifyDataSetChanged();
                                srl.setEnabled(true);
                            }
                        },3000);
                    }
                    else
                    {
//                        dialog.dismissDialog();
                        t1.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    dialog.dismissDialog();
                    t1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<OldPaper>> call, Throwable t) {
                dialog.dismissDialog();
                t1.setVisibility(View.GONE);
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