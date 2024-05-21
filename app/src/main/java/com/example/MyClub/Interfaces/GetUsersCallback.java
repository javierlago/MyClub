package com.example.MyClub.Interfaces;

import com.example.MyClub.Models.User;

import java.util.ArrayList;

public interface GetUsersCallback {

    void onSuccess(ArrayList<User> users);
    void onFailure(String errorMessage);

}
