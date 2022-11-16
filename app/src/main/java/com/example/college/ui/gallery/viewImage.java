package com.example.college.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.college.LoadingDialog;
import com.example.college.R;

import java.util.ArrayList;
import java.util.List;

public class viewImage extends AppCompatActivity {

    ViewPager imageSlider;
    gallerySliderAdapter adapter;
    Toolbar toolbar;
    List<String> imagePaths;
    List<String> imageIds;
    RelativeLayout rl;
    int position;
    LoadingDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(Color.BLACK);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        dialog1=new LoadingDialog(this);
        toolbar.setVisibility(View.GONE);

        rl = findViewById(R.id.gallery_rl);
        imageSlider = findViewById(R.id.gallery_view_pager);

        imagePaths=new ArrayList<>();
        imageIds=new ArrayList<>();

        imagePaths = getIntent().getStringArrayListExtra("imagePaths");
        imageIds = getIntent().getStringArrayListExtra("imageIds");
        int position = getIntent().getIntExtra("position", 0);

        adapter = new gallerySliderAdapter(this, imagePaths, toolbar, rl);
        imageSlider.setAdapter(adapter);
        imageSlider.setCurrentItem(position);
        imageSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}