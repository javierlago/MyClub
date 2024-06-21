package com.example.MyClub.Controlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.MyClub.MainActivity;
import com.example.MyClub.Views.Athlete.AthleteActivity;
import com.example.MyClub.Views.Manager.DirectivoActivity;
import com.example.MyClub.Views.Trainer.TrainerActivity;
import com.example.conectarapi.R;

public class ViewsController {

    Context context;
    String userRol;
    Activity activity;


    DialogWindow dialogWindow = new DialogWindow();


    public ViewsController(Context context, String userRol,Activity activity) {
        this.context = context;
        this.userRol = userRol;
        this.activity = activity;
    }


    public void backToMain(){
        Intent intent = new Intent();
        if (Constantes.getManager(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, DirectivoActivity.class);
        } else if (Constantes.getAthlete(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, AthleteActivity.class);
        } else if (Constantes.getTrainer(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, TrainerActivity.class);
        }
        context.startActivity(intent);
    }

    public void backToLoginWithDialog() {
        dialogWindow.acceptCancelWindow(context,context.getResources().getString(R.string.warnig),context.getResources().getString(R.string.logout), new DialogListener() {
            @Override
            public void onApceptSelected() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }

            @Override
            public void onCancelSelected() {
            }
        });
    }

}
