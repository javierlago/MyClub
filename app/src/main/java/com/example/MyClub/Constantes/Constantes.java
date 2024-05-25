package com.example.MyClub.Constantes;

import android.content.Context;

import com.example.conectarapi.R;

public class Constantes {

    public static final String BASE_URL = "http://192.168.1.139/miapp/public/";

    public static String getDirectivo(Context context) {
        return context.getString(R.string.directivo);
    }
    public static String getAtleta(Context context) {
        return context.getString(R.string.atleta);
    }
    public static String getEntrenador(Context context) {
        return context.getString(R.string.entrenador);
    }

}
