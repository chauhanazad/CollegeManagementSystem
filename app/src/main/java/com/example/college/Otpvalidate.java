package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Otpvalidate extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    TextView t1;
    static String ab="",a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvalidate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Forgot Password");
        setSupportActionBar(toolbar);

        t1=findViewById(R.id.t11);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        e5=findViewById(R.id.e5);
        e6=findViewById(R.id.e6);

        Intent i=getIntent();
        a=i.getStringExtra("pin");
        b=i.getStringExtra("email");
        t1.append(""+b);

        EditText[] edit = {e1,e2,e3,e4,e5,e6};

        e1.addTextChangedListener(new GenericTextWatcher(e1, edit,this));
        e2.addTextChangedListener(new GenericTextWatcher(e2, edit,this));
        e3.addTextChangedListener(new GenericTextWatcher(e3, edit,this));
        e4.addTextChangedListener(new GenericTextWatcher(e4, edit,this));
        e5.addTextChangedListener(new GenericTextWatcher(e5, edit,this));
        e6.addTextChangedListener(new GenericTextWatcher(e6, edit,this));

//        e1.addTextChangedListener(this);
//        e2.addTextChangedListener(this);
//        e3.addTextChangedListener(this);
//        e4.addTextChangedListener(this);
//        e5.addTextChangedListener(this);
//        e6.addTextChangedListener(this);

//        e1.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e1.getText().length() == 1)
//                    e2.requestFocus();
//                return false;
//            }
//        });
//
//        e2.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e2.getText().length() == 1) {
//                    e3.requestFocus();
//                }
//                else if(e2.getText().toString().equals(null))
//                {
//                    e2.requestFocus();
//                }
//                return false;
//            }
//        });
//        e3.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e3.getText().length() == 1)
//                    e4.requestFocus();
//                return false;
//            }
//        });
//        e4.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e4.getText().length() == 1)
//                    e5.requestFocus();
//                return false;
//            }
//        });
//        e5.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e5.getText().length() == 1)
//                    e6.requestFocus();
//                return false;
//            }
//        });
//        e6.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (e6.getText().length() == 1)
//                {
//                    ab=e1.getText().toString()+e2.getText().toString()+e3.getText().toString()
//                            +e4.getText().toString()+e5.getText().toString()+e6.getText().toString();
//
//                    Toast.makeText(Otpvalidate.this, "hii "+ab, Toast.LENGTH_SHORT).show();
//                    if(a.equals(ab)==true)
//                    {
//                        Intent i=new Intent(Otpvalidate.this,ChangePassword.class);
//                        i.putExtra("email",b);
//                        startActivity(i);
//                    }
//                    else
//                    {
//                        Toast.makeText(Otpvalidate.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                return false;
//            }
//        });
    }

    public void change(View view) {
        String ab=e1.getText().toString()+e2.getText().toString()+e3.getText().toString()
                +e4.getText().toString()+e5.getText().toString()+e6.getText().toString();

        //Toast.makeText(Otpvalidate.this, "hii "+ab, Toast.LENGTH_SHORT).show();
        if(a.equals(ab)==true)
        {
            Intent i=new Intent(Otpvalidate.this,ResetPassword.class);
            i.putExtra("email",b);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(Otpvalidate.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wrong OTP", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
            snacktv.setTextColor(getResources().getColor(R.color.white));
            snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}