package com.example.MyClub.Constants;

import android.content.Context;

import com.example.conectarapi.R;

public class Constantes {

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
