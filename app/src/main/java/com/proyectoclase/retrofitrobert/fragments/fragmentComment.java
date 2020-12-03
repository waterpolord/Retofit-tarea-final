package com.proyectoclase.retrofitrobert.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.proyectoclase.retrofitrobert.APIService;
import com.proyectoclase.retrofitrobert.models.Comment;
import com.proyectoclase.retrofitrobert.adapters.CommentAdapter;
import com.proyectoclase.retrofitrobert.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragmentComment extends Fragment {

    private RecyclerView recyclerView;
    private List<Comment> elments;
    TextView txtPost,idPost;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.comment_recycler_view, container, false);
        idPost = rootView.findViewById(R.id.txtidPostC);
        txtPost = rootView.findViewById(R.id.txtpostC);
        recyclerView = rootView.findViewById(R.id.commentsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Comment>> listCall = retrofit.create(APIService.class).getCommentByPostId(getActivity().getIntent().getExtras().getInt("Position") + 1);

        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                elments = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                CommentAdapter adapter = new CommentAdapter(getActivity(),elments);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
        idPost.setText(String.valueOf(getActivity().getIntent().getExtras().getInt("Position")+1));
        txtPost.setText(getActivity().getIntent().getExtras().getString("Title"));


        return rootView;
    }

}
