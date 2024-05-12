package com.example.MyClub.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.MainActivity;
import com.example.conectarapi.R;

public class DirectivoActivity extends AppCompatActivity implements View.OnClickListener {



    Button botonGestionDirectivos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directivo);
        botonGestionDirectivos = findViewById(R.id.btn_gestion_directivo);
        botonGestionDirectivos.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        if(v==botonGestionDirectivos){

            intent = new Intent(DirectivoActivity.this, ListUserActivity.class);
            startActivity(intent);

        }
    }
}