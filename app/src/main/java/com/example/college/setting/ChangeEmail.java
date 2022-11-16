package com.example.college.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.college.LoadingDialog;
import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangeEmail extends AppCompatActivity {

    EditText email;
    LoadingDialog dialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Setting");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String defemail = sharedPreferences.getString("email", "no");
        email=findViewById(R.id.email);
        email.setText(defemail);
        dialog=new LoadingDialog(this);
    }

    public void senddOTP(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String email1 = sharedPreferences.getString("email", "no");
        if (email.getText().toString().length() == 0) {
            email.requestFocus();
            email.setError("Field cannot be empty");
        }
        else if(!email.getText().toString().matches(emailPattern))
        {
            email.requestFocus();
            email.setError("Invalid Email");
        }
        else if(email.getText().toString().equals(email1))
        {
            email.requestFocus();
            email.setError("Enter New Email");
        }
        else {
            dialog.startAlertDialog();
            Retrofit retrofit= MyRetrofit.getRetrofit();
            APIclient api=retrofit.create(APIclient.class);
            Call<String> call=api.sendMail(email.getText().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
                    if(!a.equals(""))
                    {
                        dialog.dismissDialog();
                        Intent i = new Intent(ChangeEmail.this, VerifyEmail.class);
                        i.putExtra("email", email.getText().toString());
                        i.putExtra("otp", a);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dialog.dismissDialog();
                    Toast.makeText(ChangeEmail.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}