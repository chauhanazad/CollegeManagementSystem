package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResetPassword extends AppCompatActivity{

    EditText e1,e2;
    String email;
    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Forgot Password");
        setSupportActionBar(toolbar);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        dialog=new LoadingDialog(this);
        Intent i=getIntent();
        email=i.getStringExtra("email");

    }

    public void changePassword(View view) {
        String pass1,pass2;
        pass1=e1.getText().toString().trim();
        pass2=e2.getText().toString().trim();
        if (pass1.equals("")) {
            e1.requestFocus();
            e1.setError("Field cannot be empty");
        } else if (pass2.equals("")) {
            e2.requestFocus();
            e2.setError("Field cannot be empty");
        }
        else if(pass1.equals(pass2))
        {
            dialog.startAlertDialog();
            Retrofit retrofit= MyRetrofit.getRetrofit();
            APIclient api=retrofit.create(APIclient.class);
            Call<String> call=api.resetPassword(email,pass2);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
                    if(a.equals("success"))
                    {
                        dialog.dismissDialog();
                        Toast.makeText(ResetPassword.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        dialog.dismissDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something Went Wrong", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                        TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                        snacktv.setTextColor(getResources().getColor(R.color.white));
                        snackbar.show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dialog.dismissDialog();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                    snacktv.setTextColor(getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}