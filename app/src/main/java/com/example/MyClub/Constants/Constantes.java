package com.example.MyClub.Constants;

import android.content.Context;

import com.example.conectarapi.R;

public class Constantes {
    public static String userRol;
    public static int userId;

    public static void setUserRol(String userRol) {
        Constantes.userRol = userRol;
    }

    public static void setUserId(int userId) {
        Constantes.userId = userId;
    }

    public static final String BASE_URL = "http://192.168.1.139/miapp/public/api/";

    public static String getManager(Context context) {
        return context.getString(R.string.directivo);
    }

    public static String getAthlete(Context context) {
        return context.getString(R.string.atleta);
    }

    public static String getTrainer(Context context) {
        return context.getString(R.string.entrenador);
    }

}
