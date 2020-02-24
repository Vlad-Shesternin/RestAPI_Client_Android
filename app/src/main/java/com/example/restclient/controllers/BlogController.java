package com.example.restclient.controllers;

import android.widget.TextView;

import com.example.restclient.models.Blog;
import com.example.restclient.repositories.BlogAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlogController implements Callback<List<Blog>> {

    static final String BASE_URL = "http://192.168.43.183:8080";
    private TextView mTextView;

    public BlogController(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BlogAPI blogAPI = retrofit.create(BlogAPI.class);

        Call<List<Blog>> call = blogAPI.getBlogs();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
        if (response.isSuccessful()) {
            List<Blog> blogList = response.body();
            for (Blog blog : blogList) {
                mTextView.append("Id: " + blog.getId() + ", title: " + blog.getTitle() + ", content: " + blog.getContent() + "\n");
            }

        } else {
            System.err.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Blog>> call, Throwable t) {
        t.printStackTrace();
    }
}
