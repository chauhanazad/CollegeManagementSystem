package com.example.college.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class ViewIssueList extends AppCompatActivity {

    List<Library> bookDetailsList;
    List<Library> bookList;
    RecyclerView IssuedBooksRv;
    issuedBooksApadter adapter;
    TextView t1,t2;
    View v;
//    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_issue_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Your Issued Books");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

//        dialog=new LoadingDialog(this);
        t1=findViewById(R.id.info);
        t2=findViewById(R.id.head);
        v=findViewById(R.id.head1);
        bookDetailsList = new ArrayList<>();
        bookList = new ArrayList<>();

        IssuedBooksRv = findViewById(R.id.rv);
        IssuedBooksRv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new issuedBooksApadter(ViewIssueList.this, bookList);
        IssuedBooksRv.setAdapter(adapter);

        myUpdateOperation();
//        dialog.startAlertDialog();

    }

    void myUpdateOperation()
    {
        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);

        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "no");

        Call<List<Library>> call = api.getIssuedBookOfStudent(email);
        call.enqueue(new Callback<List<Library>>() {
            @Override
            public void onResponse(Call<List<Library>> call, Response<List<Library>> response) {

                bookDetailsList = response.body();
                if(bookDetailsList.size()>0) {

//                    dialog.dismissDialog();
                    for (Library bookDetails : bookDetailsList)
                        bookList.add(new Library(
                                bookDetails.getBook_id(), bookDetails.getSubmission_date(),bookDetails.getSub_name(),bookDetails.getIssue_date()
                        ));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.showShimmer=false;
                            adapter.notifyDataSetChanged();
                            t1.setVisibility(View.GONE);
                            t2.setVisibility(View.VISIBLE);
                            v.setVisibility(View.VISIBLE);
                        }
                    },3000);
                }
                else {
//                    dialog.dismissDialog();
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    v.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Library>> call, Throwable t) {
//                dialog.dismissDialog();
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                v.setVisibility(View.GONE);
                IssuedBooksRv.setAdapter(null);
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