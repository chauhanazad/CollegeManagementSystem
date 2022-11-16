package com.example.college.ui.gallery;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.college.IPActivity;

import java.util.List;

public class galleryAdapter extends BaseAdapter {
    Context c;
    List<String> imageList;

    public galleryAdapter(Context c, List<String> imageList) {
        this.c = c;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(c);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setForegroundGravity(View.TEXT_ALIGNMENT_CENTER);
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.O)
        {
            imageView.setLayoutParams(new GridView.LayoutParams(240,240));
        }
        else
        {

            imageView.setLayoutParams(new GridView.LayoutParams(365,365));
        }
        //imageView.setLayoutParams(new GridView.LayoutParams(270,270));
        Glide.with(c)
                .load("http://"+ IPActivity.ip +"/MLP/" + imageList.get(position))
                .into(imageView);
        return imageView;
    }



    public  void clear()
    {
        imageList.clear();
    }
}
