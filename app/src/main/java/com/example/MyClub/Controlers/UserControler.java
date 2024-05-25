package com.example.MyClub.Controlers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import com.example.MyClub.Constantes.Constantes;
import com.example.MyClub.Interfaces.UserControllerCallback;
import com.example.MyClub.Interfaces.GetUserById;
import com.example.MyClub.Interfaces.GetUsersCallback;
import com.example.MyClub.Interfaces.LoginCallback;
import com.example.MyClub.Interfaces.UserApi;
import com.example.MyClub.Models.User;
import com.example.conectarapi.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** @noinspection CallToPrintStackTrace*/
public class UserControler {

    private final UserApi apiService= ApiClient.getUserApi();

    String rol;


    int userId;


    String message;

    Context context;

    public UserControler(Context context) {
        this.context = context;
    }




    public void login(String usuario, String password, LoginCallback callback){






        // Hacer la llamada a la API
        Call<ResponseBody> call = apiService.validateUser(usuario,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        String jsonResponse = response.body().string();
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        rol = jsonObject.getString("respuesta");
                        userId = jsonObject.getInt("id");

                       // Toast.makeText(context, rol, Toast.LENGTH_SHORT).show();
                        callback.onSuccess(rol,userId);
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
    public void getUsers(String route, GetUsersCallback getUsersCallback){



        // Hacer la llamada a la API según el valor de route
        Call<JsonObject> call;
        // manejar a la routa que deseamos llamar para obtener un listado diferebte

        if (Constantes.getDirectivo(context).equalsIgnoreCase(route)) {
            call = apiService.getUsersDirectivo();
        } else if (Constantes.getEntrenador(context).equals(route)) {
            call = apiService.getUsersEntrenador();
        } else if (Constantes.getAtleta(context).equals(route)) {
            call = apiService.getUsersAtleta();
        } else {
            getUsersCallback.onFailure(context.getResources().getString(R.string.error_route));
            return;
        }
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


    public void deleteUser(int userId, UserControllerCallback deleteUserCallBack) {

        Call<ResponseBody> call = apiService.deleteUserById(userId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        message = jsonObject.getString("message");
                        // Toast.makeText(context, rol, Toast.LENGTH_SHORT).show();
                        deleteUserCallBack.onSucces(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        deleteUserCallBack.onFailure(e.getMessage());
                    } catch (IOException e) {
                        // Manejar error de lectura de respuesta
                        e.printStackTrace();
                        deleteUserCallBack.onFailure(e.getMessage());
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
                deleteUserCallBack.onFailure(t.getMessage());

            }


        });


    }

    public void getUserById(int userId, GetUserById getUserById){

        Call<JsonObject> call = apiService.getUserById(userId);


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseObject = response.body();;
                    if (responseObject.has("user")) {
                        JsonObject userObject = responseObject.getAsJsonObject("user");
                        Gson gson = new Gson();
                        User user = gson.fromJson(userObject, User.class);
                        getUserById.onSuccess(user);
                    } else {
                        getUserById.onFailure("No se encontró la clave 'user' en la respuesta");
                    }
                } else {
                    getUserById.onFailure("Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                getUserById.onFailure(t.getMessage());

            }
        });


    }

    public void updateUser(int userId, User user, UserControllerCallback userControllerCallback, Context context){

        Call<Void> call = apiService.updateUser(userId,user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.isSuccessful()){
                    userControllerCallback.onSucces(context.getResources().getString(R.string.user_updated));
                }else{
                    userControllerCallback.onFailure(context.getString(R.string.fail_update_user));
                }

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                userControllerCallback.onFailure(context.getString(R.string.fail_update_user));
            }
        });


    }

    public void createUser(User user, UserControllerCallback userControllerCallback, Context context) {

        Call<ResponseBody> call = apiService.createUser(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null) {
                            String jsonResponse = responseBody.string();
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            message = jsonObject.getString("message");
                            userControllerCallback.onSucces(message);
                        } else {
                            userControllerCallback.onFailure("La respuesta no tiene cuerpo");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        userControllerCallback.onFailure(e.getMessage());
                    } catch (IOException e) {
                        // Manejar error de lectura de respuesta
                        e.printStackTrace();
                    }
                } else {
                    userControllerCallback.onFailure(message);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                userControllerCallback.onFailure(context.getString(R.string.fail_create_user));
            }
        });

    }

}
