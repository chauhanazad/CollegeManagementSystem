package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.example.college.notification.MyMessagingService;
import com.example.college.student.student_details;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText e1, e2;
    String response, name, image;

    student_details l;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String expression = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
    String email,password;
    LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.ed_email);
        e2 = findViewById(R.id.ed_pass);
        dialog=new LoadingDialog(this);
    }
    void check_login()
    {
        check.writeshared(this, "login", "done");
        check.writeshared(this, "name", name);
        check.writeshared(this, "email", e1.getText().toString());
        check.writeshared(this, "image_path", image);
//
//        Toast.makeText(this, "hii"+name, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void login(View view)
    {
        email=e1.getText().toString().trim();
        password=e2.getText().toString().trim();
        if (email.equals("") && !email.matches(emailPattern)) {
            e1.requestFocus();
            e1.setError("Field cannot be empty");
        } else if (password.equals("")) {
            e2.requestFocus();
            e2.setError("Field cannot be empty");
        }
        else
        {
            dialog.startAlertDialog();
//
            Retrofit retrofit = MyRetrofit.getRetrofit();
//
            APIclient api = retrofit.create(APIclient.class);
            Call<student_details> call = api.login(e1.getText().toString(), e2.getText().toString());
            call.enqueue(new Callback<student_details>() {
                @Override
                public void onResponse(Call<student_details> call, Response<student_details> response1) {
                    l = response1.body();
                    response = l.getResponse();
                    if (response.equals("success")) {
                        //Toast.makeText(Login_Activity.this, "bye "+type, Toast.LENGTH_SHORT).show();
                        name = l.getFirstname() + " " + l.getMiddlename() + " " + l.getLastname();
                        image = l.getImage_path();
                        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
                        String token = sharedPreferences.getString("token", "no");
                        Retrofit retrofit= MyRetrofit.getRetrofit();
                        APIclient api=retrofit.create(APIclient.class);
                        Call<String> call1=api.insertKey(token,email);
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body().equals("success")) {
                                    dialog.dismissDialog();
                                    check_login();
                                }
                                else
                                {
                                    dialog.dismissDialog();
                                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Try After Sometimes", Snackbar.LENGTH_SHORT);
                                    snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                                    TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                                    snacktv.setTextColor(getResources().getColor(R.color.white));
                                    snackbar.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                dialog.dismissDialog();
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "inner Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                                snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                                TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                                snacktv.setTextColor(getResources().getColor(R.color.white));
                                snackbar.show();
                            }
                        });

                    } else if (response.equals("unsuccess")) {
                        dialog.dismissDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Email and Password is Incorrect", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                        TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                        snacktv.setTextColor(getResources().getColor(R.color.white));
                        snackbar.show();
                    }
                }

                @Override
                public void onFailure(Call<student_details> call, Throwable t) {
                    dialog.dismissDialog();
//                    Toast.makeText(LoginActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
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
        //super.onBackPressed();
            finish();

    }

    public void forgotPassword(View view) {
        Intent i=new Intent(this,forgotPassword.class);
        startActivity(i);
    }
}