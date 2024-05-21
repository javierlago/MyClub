package com.example.MyClub.Interfaces;

import com.example.MyClub.Models.User;

import java.util.ArrayList;

public interface GetUserById {

    void onSuccess(User user);
    void onFailure(String errorMessage);


}
