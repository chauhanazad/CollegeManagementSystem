package com.example.college.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FaqActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Question");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        e1=findViewById(R.id.question_faq);
        e2=findViewById(R.id.title_faq);
        b1=findViewById(R.id.b1);
    }

    public void sumitQuestion(View view) {
        if(e1.getText().toString().trim().length()==0)
        {
            e1.requestFocus();
            e1.setError("Field cannot be empty");
        }
        else if(e2.getText().toString().trim().length()==0)
        {
            e2.requestFocus();
            e2.setError("Field cannot be empty");
        }
        else
        {
            SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
            String email = sharedPreferences.getString("email", "no");
            Retrofit retrofit= MyRetrofit.getRetrofit();
            APIclient api=retrofit.create(APIclient.class);
            Call<String> call=api.insertFaq(e1.getText().toString(),e2.getText().toString(),email);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
                    if(a.equals("success"))
                    {
                        b1.setEnabled(false);
                        Toast.makeText(FaqActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(FaqActivity.this, "Try After Sometime", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(FaqActivity.this, "Server Not Responding Or Check Your Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}