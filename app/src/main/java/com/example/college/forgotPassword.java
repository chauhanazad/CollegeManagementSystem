package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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

public class forgotPassword extends AppCompatActivity {

    EditText email;
    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Forgot Password");
        setSupportActionBar(toolbar);

        dialog=new LoadingDialog(this);
        email=findViewById(R.id.email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void checkdata(View view) {
        String mail=email.getText().toString().trim();
        if (mail.equals("")) {
            email.requestFocus();
            email.setError("Field cannot be empty");
        }
        else
        {
            dialog.startAlertDialog();
            Retrofit retrofit = MyRetrofit.getRetrofit();

            APIclient api = retrofit.create(APIclient.class);
            Call<String> call = api.forgotPassword(email.getText().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
//                    Toast.makeText(forgotPassword.this, "hello "+response.body(), Toast.LENGTH_SHORT).show();
                    if(a.equals("fail")==true)
                    {
                        dialog.dismissDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Enter your Registrated Email", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                        TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                        snacktv.setTextColor(getResources().getColor(R.color.white));
                        snackbar.show();
                    }

                    else
                    {
                        dialog.dismissDialog();
                        Intent i=new Intent(forgotPassword.this,Otpvalidate.class);
                        i.putExtra("email",email.getText().toString());
                        i.putExtra("pin",a);
                        startActivity(i);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dialog.dismissDialog();
//                    Toast.makeText(forgotPassword.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                    snacktv.setTextColor(getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });
        }
    }
}