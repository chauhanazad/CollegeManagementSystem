package com.example.college.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePassword extends AppCompatActivity {

    EditText e1,e2,e3;
    String email;
    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Setting");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "no");

        dialog=new LoadingDialog(this);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);

    }

    public void changedPassword(View view) {
        int i=0;

        if (e1.getText().toString().length() == 0) {
            e1.requestFocus();
            e1.setError("Field cannot be empty");
            i=1;
        }
        else if(e2.getText().toString().length()==0)
        {
            e2.requestFocus();
            e2.setError("Field cannot be empty");
            i=1;
        }
        else if(e2.getText().toString().length()<8 || !isValidPassword(e2.getText().toString()))
        {
            e2.requestFocus();
            e2.setError("Password must contains: " +
                    "a Special Symbol " +
                    "a Captial and Small letter " +
                    "a Number " +
                    "and minimum 8 lenght");
        }
        else if(e3.getText().toString().length()==0)
        {
            e3.requestFocus();
            e3.setError("Field cannot be empty");
            i=1;
        }
        else if(e3.getText().toString().length()<8 || !isValidPassword(e3.getText().toString()))
        {
            e2.requestFocus();
            e2.setError("Password must contains: " +
                    "a Special Symbol " +
                    "a Captial and Small letter " +
                    "a Number " +
                    "and minimum 8 lenght");
        }
        else
        {
            dialog.startAlertDialog();
            Retrofit retrofit= MyRetrofit.getRetrofit();
            APIclient api=retrofit.create(APIclient.class);
            Call<String> call=api.changePassword(email,e1.getText().toString(),e3.getText().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
                    if(a.equals("success")==true)
                    {
                        dialog.dismissDialog();
                        Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if (a.equals("fail")==true)
                    {
                        dialog.dismissDialog();
                        Toast.makeText(ChangePassword.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dialog.dismissDialog();
                    Toast.makeText(ChangePassword.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}