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

    @GET("user_index_directivo")
    Call<JsonObject> getUsersDirectivo();

    @GET("user_index_entrenador")
    Call<JsonObject> getUsersEntrenador();

    @GET("user_index_atleta")
    Call<JsonObject> getUsersAtleta();

    @GET("user_get_by_id/{id}")
    Call<JsonObject> getUserById(@Path("id") int userId);

    @DELETE("user_delete/{id}")
    Call<ResponseBody> deleteUserById(@Path("id") int userId);



        @PUT("user_update/{id}")
        Call<Void> updateUser(@Path("id") int id, @Body User user);


}


