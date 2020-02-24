package com.example.restclient.repositories;

import com.example.restclient.models.Blog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BlogAPI {
    @GET("/blog")
    Call<List<Blog>> getBlogs();
}
