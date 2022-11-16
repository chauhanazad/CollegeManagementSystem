package com.example.college.network;


import com.example.college.IPActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public static Retrofit retrofit=null;
    public static String baseURl;

    public static Retrofit getRetrofit() {
        baseURl="http://"+ IPActivity.ip +"/MLP/";
        if(retrofit==null)
        {
            Retrofit.Builder builder=new Retrofit.Builder();
            builder.baseUrl(baseURl);
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit=builder.build();
        }
        return retrofit;
    }
}