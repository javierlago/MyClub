package com.example.MyClub.Controlers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.MyClub.Interfaces.AllPostCallBack;
import com.example.MyClub.Interfaces.CallbackController;
import com.example.MyClub.Interfaces.RetrofitApi;
import com.example.MyClub.Models.Post;
import com.example.conectarapi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostController {

    String message;
    private final RetrofitApi apiService = ApiClient.getApiClient();

    Context context;

    public PostController(Context context) {
        this.context = context;
    }

    public void getAllPosts(AllPostCallBack allPostCallBack) {


        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    allPostCallBack.onSuccess(posts);

                } else {
                    allPostCallBack.onFailure(context.getString(R.string.error_msg_post));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                allPostCallBack.onFailure(t.getMessage());

            }
        });


    }

    public void getAllPostsWhitoutTrainig(AllPostCallBack allPostCallBack) {

        Call<List<Post>> call = apiService.getAllPostsWhitoutTrainig();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    allPostCallBack.onSuccess(posts);

                } else {
                    allPostCallBack.onFailure(context.getString(R.string.error_msg_post));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                allPostCallBack.onFailure(t.getMessage());

            }
        });


    }

    public void createPost(Post post, CallbackController callbackController) {
        Call<ResponseBody> call = apiService.createPost(post);
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
                            callbackController.onSucces(message);
                        } else {
                            callbackController.onFailure("La respuesta no tiene cuerpo");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callbackController.onFailure(e.getMessage());
                    } catch (IOException e) {
                        // Manejar error de lectura de respuesta
                        e.printStackTrace();
                    }
                } else {
                    callbackController.onFailure(message);
                }

            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                callbackController.onFailure(t.getMessage());
            }
        });
    }
    public void createPostWhithExercises(Post post, CallbackController callbackController) {
        Call<ResponseBody> call = apiService.createPostWithExercise(post);
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
                            callbackController.onSucces(message);
                        } else {
                            callbackController.onFailure("La respuesta no tiene cuerpo");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callbackController.onFailure(e.getMessage());
                    } catch (IOException e) {
                        // Manejar error de lectura de respuesta
                        e.printStackTrace();
                    }
                } else {
                    callbackController.onFailure(message);
                }

            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                callbackController.onFailure(t.getMessage());
            }
        });
    }
}
