package com.example.MyClub.Views.Directivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Controlers.StartBarController;
import com.example.MyClub.Interfaces.AdapterViewButtonClickListener;
import com.example.MyClub.Views.User.ListUserActivity;
import com.example.MyClub.Views.Wall.ListWallActivity;
import com.example.conectarapi.R;

public class DirectivoActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout btnManagerManagement;
    LinearLayout bntTrainerManagement;
    LinearLayout btnAthleteManagement;
    LinearLayout btnWall;

    ImageButton logout, aboutUs, profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directivo_activity);
        initViews();
        listeners();

    }

    public void initViews() {
        btnManagerManagement = findViewById(R.id.btn_gestion_directivo);
        bntTrainerManagement = findViewById(R.id.btn_gestion_entrenador);
        btnAthleteManagement = findViewById(R.id.btn_gestion_atleta);
        btnWall = findViewById(R.id.btn_wall);

        logout = findViewById(R.id.btn_log_out);
        aboutUs = findViewById(R.id.btn_about);
        profile = findViewById(R.id.btn_profile);
    }

    public void listeners() {
        btnManagerManagement.setOnClickListener(this);
        bntTrainerManagement.setOnClickListener(this);
        btnAthleteManagement.setOnClickListener(this);
        btnWall.setOnClickListener(this);
        logout.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == btnManagerManagement) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService", Constantes.getManager(this));
            startActivity(intent);

        } else if (v == bntTrainerManagement) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService", Constantes.getTrainer(this));
            startActivity(intent);

        } else if (v == btnAthleteManagement) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService", Constantes.getAthlete(this));
            startActivity(intent);
        } else if (v == logout) {
            StartBarController.startLogOut(this);
        } else if (v == aboutUs) {
            StartBarController.aboutUs(this);
        } else if (v == profile) {
            StartBarController.editProfile(this, Constantes.getManager(this), Constantes.getManager(this));
        } else if (v== btnWall) {
            intent = new Intent(DirectivoActivity.this, ListWallActivity.class);
            intent.putExtra("ApiService", Constantes.getManager(this));
            startActivity(intent);
        }
    }


}