package com.example.college;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class check {
    public static void writeshared(Context c, String key, String name)
    {

        SharedPreferences spf=c.getSharedPreferences("name",MODE_PRIVATE);
        SharedPreferences.Editor ed=spf.edit();
        ed.putString(key,name);
        ed.commit();
    }
}
