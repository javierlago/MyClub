package com.example.MyClub.Controlers;

import com.example.MyClub.Constants.Constantes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.MyClub.Interfaces.RetrofitApi;


public class ApiClient {

    private static Retrofit retrofit;

    static {
        // Configuración del interceptor de logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static RetrofitApi getApiClient() {
        return retrofit.create(RetrofitApi.class);
    }


}
