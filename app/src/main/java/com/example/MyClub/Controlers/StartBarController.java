package com.example.MyClub.Controlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.MyClub.Interfaces.GetUserById;
import com.example.MyClub.MainActivity;
import com.example.MyClub.Models.User;
import com.example.MyClub.Views.User.EditUserActivity;
import com.example.conectarapi.R;

public class StartBarController {

    static Context context;
    public static void startLogOut(Context context) {
        DialogWindow dialogWindows = new DialogWindow();
        dialogWindows.acceptCancelWindow(context, context.getResources().getString(R.string.exit), context.getResources().getString(R.string.exit_message), new DialogListener() {
            @Override
            public void onApceptSelected() {
                SharedPreferences preferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onCancelSelected() {

            }
        });

    }

    public static void aboutUs(Context context) {
        DialogWindow dialogWindows = new DialogWindow();
        dialogWindows.infoWindow(context, context.getResources().getString(R.string.about_us), context.getResources().getString(R.string.about_us_message));
    }

    public static void editProfile(Context context, String userRol, String userRolApi) {
        UserControler userControler = new UserControler(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        userControler.getUserById(userId, new GetUserById() {
            @Override
            public void onSuccess(User user) {
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("ApiService", userRolApi);
                intent.putExtra("rol", userRol);
                intent.putExtra("user", user);
                context.startActivity(intent);

            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });


    }





}
