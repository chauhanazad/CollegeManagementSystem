package com.example.college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.college.assignment.viewAssignmentsList;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.example.college.notice.ViewNotice;
import com.example.college.setting.Setting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TextView nameHeader, emailHeader;
    CircularImageView imageHeader;
    NavigationView navigationView;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        v=navigationView.inflateHeaderView(R.layout.nav_header_main);
        imageHeader=v.findViewById(R.id.imageView1);
        nameHeader=v.findViewById(R.id.name_text);
        emailHeader=v.findViewById(R.id.mail_text);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);





    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    void refresh()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "no");
        String email = sharedPreferences.getString("email", "no");
        String imagePath = sharedPreferences.getString("image_path", "no");


        Glide.with(v.getContext())
                .load("http://"+IPActivity.ip+"/MLP/"+ imagePath)
                .into(imageHeader);
        nameHeader.setText(name);
        emailHeader.setText(email);

        Retrofit retrofit= MyRetrofit.getRetrofit();
        APIclient api=retrofit.create(APIclient.class);
        Call<String> call=api.checkExits(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data =response.body();
                if(data.equals("yes"))
                {

                }
                else
                {
                    check.writeshared(MainActivity.this, "login", "no");
                    check.writeshared(MainActivity.this, "name", "no");
                    check.writeshared(MainActivity.this, "email", "no");
                    check.writeshared(MainActivity.this, "image_path", "no");

                    Toast.makeText(MainActivity.this, "Session Out", Toast.LENGTH_SHORT).show();
                    Intent i2= new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i2);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent i=new Intent(this, Setting.class);
                startActivity(i);
                break;
            case R.id.notice:
                Intent i1=new Intent(this, ViewNotice.class);
                startActivity(i1);
                break;
            case R.id.action_logout:
                check.writeshared(this, "login", "no");
                check.writeshared(this, "name", "no");
                check.writeshared(this, "email", "no");
                check.writeshared(this, "image_path", "no");

                Intent i2= new Intent(this, LoginActivity.class);
                startActivity(i2);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}