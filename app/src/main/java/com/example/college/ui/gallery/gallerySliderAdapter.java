package com.example.college.ui.gallery;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import com.example.college.IPActivity;

import java.util.List;

public class gallerySliderAdapter extends PagerAdapter {
    Context context;
    List<String> imagePaths;
    Toolbar toolbar;
    RelativeLayout rl;

    public gallerySliderAdapter(Context context, List<String> imagePaths, Toolbar toolbar, RelativeLayout rl) {
        this.context = context;
        this.imagePaths = imagePaths;
        this.toolbar = toolbar;
        this.rl = rl;

        for (int i = 0; i < imagePaths.size(); i++) {
            Log.d("my_errors", "Pos: " + i + "Name: " + imagePaths.get(i));
        }
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        Glide.with(context)
                .load("http://"+ IPActivity.ip +"/MLP/" + imagePaths.get(position))
                .into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbar.getVisibility() == View.VISIBLE) {
                    toolbar.setVisibility(View.GONE);
                    rl.setBackgroundColor(container.getResources().getColor(android.R.color.black));
                } else {
                    toolbar.setVisibility(View.VISIBLE);

                }
            }
        });
        return imageView;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        String title = imagePaths.get(position);
        toolbar.setTitle(title.substring(imagePaths.get(position).indexOf("/") + 1));
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
