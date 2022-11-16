package com.example.college.event;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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

public class EventList extends AppCompatActivity {

    RecyclerView rv;
    TextView t1;
    SwipeRefreshLayout srl;
    Retrofit retrofit;
    APIclient client;
    List<Events> eventsDetailsList;
    List<Events> eventsList;
    eventAdapter adapter;
//    LoadingDialog dialog;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Event");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        dialog=new LoadingDialog(this);
        eventsDetailsList=new ArrayList<>();
        eventsList=new ArrayList<>();
        rv=findViewById(R.id.rv_event);
        rv.setLayoutManager(new LinearLayoutManager(this));
        t1=findViewById(R.id.info);
        srl=findViewById(R.id.swiperefresh);

        adapter = new eventAdapter(EventList.this, eventsList);
        rv.setAdapter(adapter);

        srl.setEnabled(false);
        myUpdateOperation();
        srl.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//                        Toast.makeText(EventList.this, "hii", Toast.LENGTH_SHORT).show();
                        if(n==1)
                        {
                            adapter.clear();
                        }
                        myUpdateOperation();
                        srl.setRefreshing(false);
                    }
                }
        );
    }

    void myUpdateOperation()
    {
//        dialog.startAlertDialog();
        retrofit = MyRetrofit.getRetrofit();
        client = retrofit.create(APIclient.class);

        Call<List<Events>> call = client.getEvent();
        call.enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                eventsDetailsList = response.body();
//                Toast.makeText(EventList.this, ""+eventsDetailsList.size(), Toast.LENGTH_SHORT).show();
                if(eventsDetailsList.size()>0) {
//                    dialog.dismissDialog();
                    n=1;
                    t1.setVisibility(View.GONE);

//                Log.d("myerror",response.body().toString());
                    for (Events details : eventsDetailsList)
                        eventsList.add(new Events(
                                details.getEvent_id(), details.getName(), details.getfile_path()
                        ));

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
//                    dialog.dismissDialog();
                    t1.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
//                dialog.dismissDialog();
                t1.setVisibility(View.GONE);
                rv.setAdapter(null);
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