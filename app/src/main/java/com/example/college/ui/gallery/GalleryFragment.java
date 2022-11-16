package com.example.college.ui.gallery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.college.R;
import com.example.college.network.APIclient;
import com.example.college.network.MyRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GalleryFragment extends Fragment {

    GridView GalleryRv;
    galleryAdapter adapter;
    List<Images> imagePathsList;
    ArrayList<String> imageList;
    ArrayList<String> imageIds;
    APIclient client;
    Retrofit retrofit;
    View v;
    TextView t1;
    int n=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_gallery, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();

    }
    void refresh()
    {
        imageList = new ArrayList<>();
        imageIds = new ArrayList<>();
        imagePathsList = new ArrayList<>();
        t1=v.findViewById(R.id.info);
        GalleryRv = v.findViewById(R.id.gallery_rv);
        myUpdateOperation();

    }

    public void myUpdateOperation()
    {
        if(n==1)
        {
            adapter.clear();
        }

        retrofit = MyRetrofit.getRetrofit();
        client = retrofit.create(APIclient.class);

        Call<List<Images>> call = client.getImages();
        call.enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Call<List<Images>> call, Response<List<Images>> response) {
                imagePathsList = response.body();
//                Toast.makeText(v.getContext(), ""+imagePathsList.size(), Toast.LENGTH_SHORT).show();
                if(imagePathsList.size()>0) {
                    n=1;
                    t1.setVisibility(View.GONE);
//                    Toast.makeText(getContext(), "inner", Toast.LENGTH_SHORT).show();
                    for (Images Images : imagePathsList) {
                        imageList.add(Images.getImage_paths());
                        imageIds.add(Images.getImage_id());
                    }
                    adapter = new galleryAdapter(getContext(), imageList);
                    GalleryRv.setAdapter(adapter);

                    GalleryRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), viewImage.class);
                            intent.putStringArrayListExtra("imagePaths", imageList);
                            intent.putStringArrayListExtra("imageIds", imageIds);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    t1.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<List<Images>> call, Throwable t) {
                t1.setVisibility(View.VISIBLE);
                Snackbar snackbar = Snackbar.make(v.getRootView().findViewById(android.R.id.content), "Server Not Responding Or Check Your Internet", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                TextView snacktv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                snacktv.setTextColor(getResources().getColor(R.color.white));
                snackbar.show();
            }
        });
    }
}