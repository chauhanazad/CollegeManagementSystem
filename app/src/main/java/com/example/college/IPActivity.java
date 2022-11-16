package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IPActivity extends AppCompatActivity {

    EditText e1;
    public static String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_p);

        e1=findViewById(R.id.e1);

    }

    public void nextActivity(View view) {
        ip=e1.getText().toString();
        if(haveNetwork()==true)
        {
            Handler handler=new Handler();
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
                        Intent i = new Intent(IPActivity.this, LoginActivity.class);
                        startActivity(i);
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
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean haveNetwork() {
        boolean have_WIFI = false;
        boolean have_NETWORK = false;

        ConnectivityManager cn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
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
        return cn.getActiveNetworkInfo() != null && cn.getActiveNetworkInfo().isConnected();
    }
}