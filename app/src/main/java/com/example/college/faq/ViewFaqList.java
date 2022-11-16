package com.example.college.faq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
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

public class ViewFaqList extends AppCompatActivity {


    RecyclerView rv;
    TextView t1;
    LoadingDialog dialog;
    List<Faq> faqs;
    List<Faq> faqList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faq_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("FAQ");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        faqs=new ArrayList<>();
        faqList=new ArrayList<>();
        dialog=new LoadingDialog(this);
        t1=findViewById(R.id.info);
        rv=findViewById(R.id.rv_faq);
        rv.setLayoutManager(new LinearLayoutManager(this));
        myUpdateOperation();
    }

    void myUpdateOperation()
    {
        dialog.startAlertDialog();
        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);
        Call<List<Faq>> call=api.getFaqList();
        call.enqueue(new Callback<List<Faq>>() {
            @Override
            public void onResponse(Call<List<Faq>> call, Response<List<Faq>> response) {
                faqs=response.body();
                if(faqs.size()>0)
                {
                    if(faqs.get(0).getResponse().equals("")) {
                        t1.setVisibility(View.GONE);
                        dialog.dismissDialog();
                        for (Faq f : faqs) {
                            faqList.add(new Faq(f.getFaq_id(), f.getTitle(), f.getQuestion(), f.getReply()));
                        }
                        FaqAdapter adapter = new FaqAdapter(ViewFaqList.this, faqList);
                        rv.setAdapter(adapter);
                        adapter.setOnItemClickListener(new FaqAdapter.OnItemClickListener() {
                            @Override
                            public void onViewClick(int position) {
                                Intent i=new Intent(ViewFaqList.this,ViewFaqData.class);
                                i.putExtra("title",faqList.get(position).getTitle());
                                i.putExtra("question",faqList.get(position).getQuestion());
                                i.putExtra("reply",faqList.get(position).getReply());
                                startActivity(i);
                            }
                        });
                    }
                    else
                    {
                        dialog.dismissDialog();
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
            public void onFailure(Call<List<Faq>> call, Throwable t) {
                dialog.dismissDialog();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addfaq:
                Intent i=new Intent(this,FaqActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}