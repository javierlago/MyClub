package com.example.MyClub.Controlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.MyClub.Views.Atleta.AthleteActivity;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Views.Directivo.DirectivoActivity;
import com.example.MyClub.Views.Entrenador.TrainerActivity;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.conectarapi.R;

public class EditBarControler {
    public static void toStartButton(Boolean changes, Context context, String userRol, String userRolApi) {
        DialogWindow dialogWindows = new DialogWindow();


        if (changes) {
            dialogWindows.acceptCancelWindow(context, context.getResources().getString(R.string.no_saved_changes_title), context.getResources().getString(R.string.no_saved_changes_q), new DialogListener() {
                @Override
                public void onApceptSelected() {
                    if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.directivo))) {
                        Intent intent = new Intent(context, DirectivoActivity.class);
                        intent.putExtra("ApiService", userRol);
                        context.startActivity(intent);
                    } else if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.entrenador))) {
                        Intent intent = new Intent(context, TrainerActivity.class);
                        intent.putExtra("ApiService", userRol);
                        context.startActivity(intent);
                    } else if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.atleta))) {
                        Intent intent = new Intent(context, AthleteActivity.class);
                        intent.putExtra("ApiService", userRol);
                        context.startActivity(intent);
                    }

                }

                @Override
                public void onCancelSelected() {

                }
            });

        } else {
            if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.directivo))) {
                Intent intent = new Intent(context, DirectivoActivity.class);
                context.startActivity(intent);
            } else if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.entrenador))) {
                Intent intent = new Intent(context, TrainerActivity.class);
                intent.putExtra("ApiService", userRol);
                context.startActivity(intent);
            } else if (userRol.equalsIgnoreCase(context.getResources().getString(R.string.atleta))) {
                Intent intent = new Intent(context, AthleteActivity.class);
                intent.putExtra("ApiService", userRol);
                context.startActivity(intent);
            }

        }
    }

    ;

    public static void onBackButton(Boolean changes, Activity activity) {
        DialogWindow dialogWindows = new DialogWindow();
        if (changes) {
            dialogWindows.acceptCancelWindow(activity, activity.getResources().getString(R.string.no_saved_changes_title), activity.getResources().getString(R.string.no_saved_changes_q), new DialogListener() {
                @Override
                public void onApceptSelected() {
                    activity.onBackPressed();
                }

                @Override
                public void onCancelSelected() {

                }
            });

        } else {
            activity.onBackPressed();
        }


    }


};
