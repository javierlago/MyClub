package com.example.MyClub.controlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.MyClub.Constantes.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.MyClub.interfaces.GetUsersCallback;
import com.example.MyClub.interfaces.LoginCallback;
import com.example.MyClub.interfaces.UserApi;
import com.example.MyClub.models.User;
import com.example.MyClub.views.ListUserActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserControler {

    private UserApi apiService;

    String rol = null;



    public UserControler() {
    }

    public void login(String usuario, String password, LoginCallback callback){

        apiService = ApiClient.getUserApi();




        // Hacer la llamada a la API
        Call<ResponseBody> call = apiService.validateUser(usuario,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        rol = jsonObject.getString("respuesta");
                       // Toast.makeText(context, rol, Toast.LENGTH_SHORT).show();
                        callback.onSuccess(rol);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.onFailure(e.getMessage());
                    } catch (IOException e) {
                        // Manejar error de lectura de respuesta
                        e.printStackTrace();
                        callback.onFailure(e.getMessage());
                    }
                } else {
                    // El código de estado de la respuesta no es exitoso
                    int statusCode = response.code();
                    Log.e("Response Error", "Código de estado no exitoso: " + statusCode);

                    // Si deseas obtener más detalles sobre el error, puedes imprimir la respuesta completa
                    try {
                        Log.e("Response Error", "Respuesta completa: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();

                    }




                }
                }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.println(Log.ERROR,"error",t.getMessage());
                callback.onFailure(t.getMessage());

            }
        });

    }
    public void getDirectivos(GetUsersCallback getUsersCallback){

        apiService = ApiClient.getUserApi();

        // Hacer la llamada a la API
        Call<JsonObject> call = apiService.getUsersDirectivo();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject responseObject = response.body();
                    if (responseObject != null && responseObject.has("users")) {
                        JsonArray usersArray = responseObject.getAsJsonArray("users");
                        Gson gson = new Gson();
                        ArrayList<User> users = gson.fromJson(usersArray, new TypeToken<ArrayList<User>>(){}.getType());
                        getUsersCallback.onSuccess(users);
                    } else {
                        getUsersCallback.onFailure("No se encontró la clave 'users' en la respuesta");
                    }
                } else {
                    getUsersCallback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                getUsersCallback.onFailure(t.getMessage());


            }
        });

    }


}
