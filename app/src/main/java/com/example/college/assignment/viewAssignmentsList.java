package com.example.college.assignment;

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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.CheckData;
import com.example.college.IPActivity;
import com.example.college.LoadingDialog;
import com.example.college.LoginActivity;
import com.example.college.R;
import com.example.college.SplashAcitivty;
import com.example.college.check;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class viewAssignmentsList extends AppCompatActivity {

    RecyclerView assignmentRv;
    assignmentAdapter adapter;
    List<assignment> assignmentsDetailsList;
    List<assignment> assignmentsList;
    Retrofit retrofit;
    TextView t1;
    APIclient client;
    SwipeRefreshLayout srl;
//    LoadingDialog dialog;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignments_list);

        assignmentsDetailsList = new ArrayList<>();
        assignmentsList = new ArrayList<>();


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignment");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        dialog=new LoadingDialog(this);
        assignmentRv = findViewById(R.id.rv_assignment);
        assignmentRv.setLayoutManager(new LinearLayoutManager(this));
        t1=findViewById(R.id.info4);

        adapter = new assignmentAdapter(viewAssignmentsList.this, assignmentsList);
        assignmentRv.setAdapter(adapter);

        srl=findViewById(R.id.swiperefresh);
        srl.setEnabled(false);
        myUpdateOperation();
        srl.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//                        Toast.makeText(viewFacultyList.this, "hii", Toast.LENGTH_SHORT).show();
                        if(n==1) {
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
        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "no");

        retrofit = MyRetrofit.getRetrofit();
        client = retrofit.create(APIclient.class);


        Call<List<assignment>> call = client.getAssignmentsForStudent(email);
        call.enqueue(new Callback<List<assignment>>() {
            @Override
            public void onResponse(Call<List<assignment>> call, Response<List<assignment>> response) {
                if (response.body() != null) {

                    assignmentsDetailsList = response.body();
//                    Toast.makeText(viewAssignmentsList.this, "" + assignmentsDetailsList.get(0).getResponse(), Toast.LENGTH_SHORT).show();
                    if(!assignmentsDetailsList.get(0).getResponse().equals("failure")) {
                        if (assignmentsDetailsList.size() > 0) {
//                            dialog.dismissDialog();
                            t1.setVisibility(View.GONE);
                            n=1;
                            for (assignment details : assignmentsDetailsList) {
                                assignmentsList.add(new assignment(
                                        details.getAssignment_id(), details.getSub_name(), details.getImage_path(), details.getSem_name()
                                ));
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
//                            dialog.dismissDialog();
                            t1.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
//                        dialog.dismissDialog();
                        t1.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
//                    dialog.dismissDialog();
                    t1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<assignment>> call, Throwable t) {
//                dialog.dismissDialog();
                t1.setVisibility(View.GONE);
                assignmentRv.setAdapter(null);
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