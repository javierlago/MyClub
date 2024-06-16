package com.example.MyClub.Interfaces;

import com.example.MyClub.Models.Post;

import java.util.List;

public interface AllPostCallBack {
    void onSuccess(List<Post> postList);

    void onFailure(String errorMessage);


}
