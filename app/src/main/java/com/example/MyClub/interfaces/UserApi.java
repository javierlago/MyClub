package com.example.MyClub.interfaces;

import com.example.MyClub.models.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Header;


public interface UserApi {



    // Peticion para validar la conexion de un usuario
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> validateUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("user_index_directivo")
    Call<JsonObject> getUsersDirectivo();


}


