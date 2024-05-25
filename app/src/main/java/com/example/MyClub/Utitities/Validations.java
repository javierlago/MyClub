package com.example.MyClub.Utitities;

import androidx.annotation.Nullable;

public class Validations {
    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}
