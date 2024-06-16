package com.example.MyClub.Controlers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.MyClub.Interfaces.AllPostCallBack;
import com.example.MyClub.Interfaces.RetrofitApi;
import com.example.MyClub.Models.Post;
import com.example.conectarapi.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostController {
    private final RetrofitApi apiService = ApiClient.getApiClient();

    Context context;

    public PostController(Context context) {
        this.context = context;
    }

    public void getAllPosts(AllPostCallBack allPostCallBack) {


        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    allPostCallBack.onSuccess(posts);

                } else {
                    allPostCallBack.onFailure(context.getString(R.string.error_msg_post));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                allPostCallBack.onFailure(t.getMessage());

            }
        });


    }

    public void getAllPostsWhitoutTrainig(AllPostCallBack allPostCallBack) {

        Call<List<Post>> call = apiService.getAllPostsWhitoutTrainig();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    allPostCallBack.onSuccess(posts);

                } else {
                    allPostCallBack.onFailure(context.getString(R.string.error_msg_post));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                allPostCallBack.onFailure(t.getMessage());

            }
        });


    }


}
