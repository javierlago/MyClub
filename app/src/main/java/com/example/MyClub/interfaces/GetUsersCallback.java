package com.example.MyClub.interfaces;

import com.example.MyClub.models.User;

import java.util.ArrayList;
import java.util.List;

public interface GetUsersCallback {

    void onSuccess(ArrayList<User> users);
    void onFailure(String errorMessage);

}
