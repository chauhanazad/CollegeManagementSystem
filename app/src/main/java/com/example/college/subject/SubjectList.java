package com.example.college.subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
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

public class SubjectList extends AppCompatActivity {

    RecyclerView rv;
    List<Subjects> subjectsList;
    TextView t1;
    LoadingDialog dialog;
    String sem_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        sem_name=getIntent().getStringExtra("sem_name");
        toolbar.setTitle(sem_name);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dialog=new LoadingDialog(this);
        subjectsList=new ArrayList<>();
        rv=findViewById(R.id.rv_subject);
        rv.setLayoutManager(new LinearLayoutManager(this));
        t1=findViewById(R.id.info);
        getData();

    }

    void getData()
    {
        dialog.startAlertDialog();
        String sem_name=getIntent().getStringExtra("sem_name");
        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);
        Call<List<Subjects>> call=api.getSujects(sem_name);
        call.enqueue(new Callback<List<Subjects>>() {
            @Override
            public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                subjectsList=response.body();
                if(subjectsList.size()>0)
                {
                    dialog.dismissDialog();
                    t1.setVisibility(View.GONE);
                    SubjectListAdapter adapter=new SubjectListAdapter(subjectsList,SubjectList.this);
                    rv.setAdapter(adapter);
                }
                else
                {
                    dialog.dismissDialog();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Try After Sometimes", Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                    snacktv.setTextColor(getResources().getColor(R.color.white));
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<List<Subjects>> call, Throwable t) {
                dialog.dismissDialog();
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