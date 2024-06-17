package com.example.MyClub.Controlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.MainActivity;
import com.example.MyClub.Views.Athlete.AthleteActivity;
import com.example.MyClub.Views.Manager.DirectivoActivity;
import com.example.MyClub.Views.Trainer.TrainerActivity;

public class ViewsController {

    Context context;
    String userRol;
    Activity activity;


    public ViewsController(Context context, String userRol,Activity activity) {
        this.context = context;
        this.userRol = userRol;
        this.activity = activity;
    }


    public Intent viewChoice(){
        Intent intent = new Intent();
        if (Constantes.getManager(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, DirectivoActivity.class);
        } else if (Constantes.getAthlete(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, AthleteActivity.class);
        } else if (Constantes.getTrainer(context).equalsIgnoreCase(userRol)) {
            intent = new Intent(activity, TrainerActivity.class);
        }
        return intent;
    }




}
