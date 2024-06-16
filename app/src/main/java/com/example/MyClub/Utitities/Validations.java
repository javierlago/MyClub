package com.example.MyClub.Utitities;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Validations {


    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public static String dateConverter(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

}
