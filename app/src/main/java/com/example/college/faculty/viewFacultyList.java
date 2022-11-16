package com.example.college.faculty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

public class viewFacultyList extends AppCompatActivity {


    RecyclerView FacultyRv;
    public List<FacultyItem> facultyList;
    Retrofit retrofit;
    APIclient client;
    TextView t1;
    facultyAdapter adapter;
    SwipeRefreshLayout srl;
//    LoadingDialog dialog;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty_list);


//        dialog=new LoadingDialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of faculty");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t1=findViewById(R.id.info);
        FacultyRv = findViewById(R.id.rv_faculty);
        FacultyRv.setLayoutManager(new LinearLayoutManager(this));
        facultyList=new ArrayList<>();
        srl=findViewById(R.id.swiperefresh);
        adapter = new facultyAdapter(viewFacultyList.this, facultyList);
        FacultyRv.setAdapter(adapter);
        srl.setEnabled(false);
        myUpdateOperation();
            srl.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
//                        Toast.makeText(viewFacultyList.this, "hii", Toast.LENGTH_SHORT).show();
                            if (n == 1) {
//                            Toast.makeText(viewFacultyList.this, "hii", Toast.LENGTH_SHORT).show();
                                adapter.clear();
                            }
                            myUpdateOperation();
                            srl.setRefreshing(false);
                        }
                    }
            );

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void myUpdateOperation()
    {
//        dialog.startAlertDialog();
        retrofit = MyRetrofit.getRetrofit();
        client = retrofit.create(APIclient.class);

        Call<List<faculty_details>> call = client.getFaculty();
        call.enqueue(new Callback<List<faculty_details>>() {
            @Override
            public void onResponse(Call<List<faculty_details>> call, Response<List<faculty_details>> response) {
                List<faculty_details> facultyDetailsList = response.body();
                if(facultyDetailsList.size()>0) {
                    n=1;

                    t1.setVisibility(View.GONE);
                    for (faculty_details details1 : facultyDetailsList) {
                        facultyList.add(new FacultyItem(
                                details1.getType(), details1.getFirstname(), details1.getMiddlename(), details1.getLastname(), details1.getEmail(), details1.getImage_path()
                        ));
                    }
//                    dialog.dismissDialog();

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

//                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT);
//                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
//                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
//                    snacktv.setTextColor(getResources().getColor(R.color.white));
//                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<List<faculty_details>> call, Throwable t) {
//                dialog.dismissDialog();
                t1.setVisibility(View.GONE);
                FacultyRv.setAdapter(null);
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                snacktv.setTextColor(getResources().getColor(R.color.white));
                snackbar.show();
            }
        });
    }
}