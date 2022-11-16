package com.example.college.subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.example.college.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectSemCAt extends AppCompatActivity {

    String a[]={"First Semester","Second Semester","Third Semester","Fourth Semester","Fifth Semester","Sixth Semester"};
    List<String> name;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_sem_c_at);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Choose Semester");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name=new ArrayList<>();
        for(int i=0;i<a.length;i++)
        {
            name.add(a[i]);
        }
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        SubjectAdapter ad=new SubjectAdapter(name,this);
        rv.setAdapter(ad);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}