package com.example.MyClub.Interfaces;

public interface LoginCallback {
    void onSuccess(String rol, int userId);
    void onFailure(String errorMessage);


}
