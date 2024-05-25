package com.example.MyClub.Interfaces;

import com.example.MyClub.Models.User;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserApi {


    // Peticion para validar la conexion de un usuario
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> validateUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("users/directivo")
    Call<JsonObject> getUsersDirectivo();

    @GET("users/entrenador")
    Call<JsonObject> getUsersEntrenador();

    @GET("users/atleta")
    Call<JsonObject> getUsersAtleta();

    @GET("users/{id}")
    Call<JsonObject> getUserById(@Path("id") int userId);

    @DELETE("users/{id}")
    Call<ResponseBody> deleteUserById(@Path("id") int userId);


    @PUT("users/{id}")
    Call<Void> updateUser(@Path("id") int id, @Body User user);

    @POST("users/create_user")
    Call<ResponseBody> createUser(@Body User user);


}


