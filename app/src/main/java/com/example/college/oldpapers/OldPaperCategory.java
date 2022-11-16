package com.example.college.oldpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.college.LoadingDialog;
import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.example.college.subject.Subjects;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OldPaperCategory extends AppCompatActivity {

    String a[]={"First Semester","Second Semester","Third Semester","Fourth Semester","Fifth Semester","Sixth Semester"};
    //    String b[]={"regular","atkt"};
    ArrayList<String> subNames, semNames;
    Spinner semesterSpinner,subjectSpinner;
    List<Subjects> subjectsList;
    LoadingDialog dialog;

    Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_paper_category);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Old Papers");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dialog=new LoadingDialog(this);
        subjectsList=new ArrayList<>();

        semNames = new ArrayList<>();


        but1=findViewById(R.id.but1);


        semesterSpinner=findViewById(R.id.s1);
        subjectSpinner=findViewById(R.id.s2);


        semNames.add("Choose Semester");

        for(int i=0;i<a.length;i++)
        {
            semNames.add(a[i]);
        }


        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semNames);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);





        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {

                    subNames = new ArrayList<>();
                    dialog.startAlertDialog();

                    subNames.add("Choose Subject");

                    final String sem_name = semNames.get(position);


                    subjectSpinner.setVisibility(View.VISIBLE);

                    Retrofit retrofit= MyRetrofit.getRetrofit();
                    APIclient client=retrofit.create(APIclient.class);
                    Call<List<Subjects>> call = client.getSujects(sem_name);
                    call.enqueue(new Callback<List<Subjects>>() {
                        @Override
                        public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                            subjectsList = response.body();
                            dialog.dismissDialog();
                            for (Subjects s : subjectsList) {
                                subNames.add(s.getSub_name());
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

                    ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(OldPaperCategory.this, android.R.layout.simple_spinner_item, subNames);
                    subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    subjectSpinner.setAdapter(subjectAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }



    public void viewList(View view) {

        if(semesterSpinner.getSelectedItem().toString().equals("Choose Semester"))
        {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Choose Semester", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
            snacktv.setTextColor(getResources().getColor(R.color.white));
            snackbar.show();
        }
        else if(subjectSpinner.getSelectedItem().toString().equals("Choose Subject"))
        {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Choose Subject", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
            snacktv.setTextColor(getResources().getColor(R.color.white));
            snackbar.show();
        }
        else
        {
            Intent i=new Intent(this,ViewOldPaperList.class);
            i.putExtra("sub_name",subjectSpinner.getSelectedItem().toString());
            startActivity(i);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }
}