package com.proyectoclase.retrofitrobert;

import com.proyectoclase.retrofitrobert.models.Comment;
import com.proyectoclase.retrofitrobert.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentByPostId(@Path("postId") int postId);
}
