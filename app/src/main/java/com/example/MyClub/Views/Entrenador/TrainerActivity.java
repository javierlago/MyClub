package com.example.MyClub.Views.Entrenador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Controlers.StartBarController;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Views.User.ListUserActivity;
import com.example.MyClub.Views.Wall.ListWallActivity;
import com.example.conectarapi.R;

public class TrainerActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout linearLayoutBtnAtletas, linearLayoutBtnEjercicios, linearLayoutBtnPost;

    ImageButton btnAcercaDe, btnPerfil, btnSalir;

    DialogWindow dialogWindows = new DialogWindow();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenador_activity);
        initViews();
        listeners();

    }


    public void initViews() {
        linearLayoutBtnAtletas = findViewById(R.id.btn_gestion_atletas_entrenador);
        linearLayoutBtnEjercicios = findViewById(R.id.btn_gestion_entrenamientos_entrenador);
        linearLayoutBtnPost = findViewById(R.id.btn_gestion_publicaciones_entrenador);
        btnAcercaDe = findViewById(R.id.btn_about);
        btnPerfil = findViewById(R.id.btn_profile);
        btnSalir = findViewById(R.id.btn_log_out);
    }

    public void listeners() {
        linearLayoutBtnAtletas.setOnClickListener(this);
        linearLayoutBtnEjercicios.setOnClickListener(this);
        linearLayoutBtnPost.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnPerfil.setOnClickListener(this);
        btnAcercaDe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == linearLayoutBtnAtletas) {
            intent = new Intent(TrainerActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService", Constantes.getAthlete(this));
            startActivity(intent);
        } else if (v == linearLayoutBtnEjercicios) {
            Toast.makeText(this, "Ejercicios", Toast.LENGTH_SHORT).show();
        } else if (v == linearLayoutBtnPost) {
            intent = new Intent(TrainerActivity.this, ListWallActivity.class);
            intent.putExtra("ApiService", Constantes.getTrainer(this));
            startActivity(intent);
        } else if (v == btnAcercaDe) {
            StartBarController.aboutUs(this);
        } else if (v == btnPerfil) {
            StartBarController.editProfile(this, Constantes.getTrainer(this), Constantes.getTrainer(this));
        } else if (v == btnSalir) {
            StartBarController.startLogOut(this);
        }

    }
}