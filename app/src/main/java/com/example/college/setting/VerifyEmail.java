package com.example.college.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.GenericTextWatcher;
import com.example.college.LoadingDialog;
import com.example.college.R;
import com.example.college.check;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerifyEmail extends AppCompatActivity{

    EditText e1,e2,e3,e4,e5,e6;
    TextView t1;
    static String a,b;
    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Verify OTP");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t1=findViewById(R.id.t11);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        e5=findViewById(R.id.e5);
        e6=findViewById(R.id.e6);
        dialog=new LoadingDialog(this);
        Intent i=getIntent();
        a=i.getStringExtra("otp");
        b=i.getStringExtra("email");
        t1.append(""+b);

        EditText[] edit = {e1,e2,e3,e4,e5,e6};

        e1.addTextChangedListener(new GenericTextWatcher(e1, edit,this));
        e2.addTextChangedListener(new GenericTextWatcher(e2, edit,this));
        e3.addTextChangedListener(new GenericTextWatcher(e3, edit,this));
        e4.addTextChangedListener(new GenericTextWatcher(e4, edit,this));
        e5.addTextChangedListener(new GenericTextWatcher(e5, edit,this));
        e6.addTextChangedListener(new GenericTextWatcher(e6, edit,this));


    }

    public void changeEmail(View view) {

        String ab=e1.getText().toString()+e2.getText().toString()+e3.getText().toString()
                +e4.getText().toString()+e5.getText().toString()+e6.getText().toString();

        //Toast.makeText(Otpvalidate.this, "hii "+ab, Toast.LENGTH_SHORT).show();
        if(a.equals(ab)==true)
        {
            dialog.startAlertDialog();
            SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
            String oldemail = sharedPreferences.getString("email", "no");
            Retrofit retrofit= MyRetrofit.getRetrofit();
            APIclient api=retrofit.create(APIclient.class);
            Call<String> call=api.changeEmailOfStudent(b,oldemail);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String a=response.body();
                    if(a.equals("success"))
                    {
                        dialog.dismissDialog();
                        Toast.makeText(VerifyEmail.this, "Email Changed Successfully", Toast.LENGTH_SHORT).show();
                        check.writeshared(VerifyEmail.this, "email",b);
                        finish();

                    }
                    else
                    {
                        dialog.dismissDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                        TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                        snacktv.setTextColor(getResources().getColor(R.color.white));
                        snackbar.show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dialog.dismissDialog();
//                    Toast.makeText(VerifyEmail.this, " hiii  "+t.toString(), Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                    snacktv.setTextColor(getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });
        }
        else
        {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wrong OTP", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
            snacktv.setTextColor(getResources().getColor(R.color.white));
            snackbar.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}