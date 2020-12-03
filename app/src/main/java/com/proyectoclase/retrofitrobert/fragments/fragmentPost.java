package com.proyectoclase.retrofitrobert.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectoclase.retrofitrobert.APIService;
import com.proyectoclase.retrofitrobert.MainActivity;
import com.proyectoclase.retrofitrobert.R;
import com.proyectoclase.retrofitrobert.adapters.PostAdapter;
import com.proyectoclase.retrofitrobert.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragmentPost extends Fragment {

    private RecyclerView recyclerView;
    private List<Post> elments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_recycler_view, container, false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Call<List<Post>> listCall = retrofit.create(APIService.class).getPosts();
        listCall.enqueue(new Callback<List<Post>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                elments = response.body();
                assert elments != null;
                elments.stream().forEach(System.out::println);
                PostAdapter adapter = new PostAdapter(getContext(),elments);
                recyclerView = rootView.findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
        return rootView;
    }
}
