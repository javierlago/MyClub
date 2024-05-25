package com.example.MyClub.Directivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Constantes.Constantes;
import com.example.MyClub.Views.ListUserActivity;
import com.example.conectarapi.R;

public class DirectivoActivity extends AppCompatActivity implements View.OnClickListener {


    Button botonGestionDirectivos;
    Button botonGestionEntrenadores;
    Button botonGestionAtletas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directivo);
        botonGestionDirectivos = findViewById(R.id.btn_gestion_directivo);
        botonGestionDirectivos.setOnClickListener(this);
        botonGestionEntrenadores = findViewById(R.id.btn_gestion_entrenador);
        botonGestionEntrenadores.setOnClickListener(this);
        botonGestionAtletas = findViewById(R.id.btn_gestion_atleta);
        botonGestionAtletas.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == botonGestionDirectivos) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService", Constantes.getDirectivo(this));
            startActivity(intent);

        } else if (v == botonGestionEntrenadores) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService",Constantes.getEntrenador(this));
            startActivity(intent);

        } else if (v == botonGestionAtletas) {
            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            intent.putExtra("ApiService",Constantes.getAtleta(this));
            startActivity(intent);

        }
    }
}