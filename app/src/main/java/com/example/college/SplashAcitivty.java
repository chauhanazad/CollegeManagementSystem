package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashAcitivty extends AppCompatActivity {

    Intent intent;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitivty);

        if(haveNetwork()==true)
        {
            handler=new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("name",MODE_PRIVATE);
                    String login_status = sharedPreferences.getString("login","no");


                    if(login_status.equals("done")) {
                        checkLogin();
                    }else {
                        intent = new Intent(SplashAcitivty.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            },1000);
        }
        else
        {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    void checkLogin() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean haveNetwork()
    {
        boolean have_WIFI=false;
        boolean have_NETWORK=false;

        ConnectivityManager cn=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        NetworkInfo infos[]=connectivityManager.getAllNetworkInfo();
//
//        for(NetworkInfo networkInfo:infos)
//        {
//            if(networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
//            {
//                if(networkInfo.isConnected())
//                {
//                    have_WIFI=true;
//                }
//            }
//            if(networkInfo.getTypeName().equalsIgnoreCase("NETWORK"))
//            {
//                if(networkInfo.isConnected())
//                {
//                    have_NETWORK=true;
//                }
//            }
//        }
        return  cn.getActiveNetworkInfo() != null && cn.getActiveNetworkInfo().isConnected();
    }
}