package com.example.MyClub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.interfaces.LoginCallback;
import com.example.MyClub.views.AtletaActivity;
import com.example.MyClub.views.DirectivoActivity;
import com.example.MyClub.views.EntrenadorActivity;
import com.example.conectarapi.R;

import com.example.MyClub.controlers.UserControler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Elementos para la pantalla principal
    EditText editTextUser;
    EditText editTextPassword;

    Button buttonLogin;
    UserControler userControler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identifier();
        listeners();
        userControler  = new UserControler();


    }




    public void identifier(){
        editTextUser = findViewById(R.id.edit_text_user);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.btn_login);
    }

    public void listeners(){
        buttonLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        if(v==buttonLogin){
            if(!editTextUser.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){


             userControler.login(editTextUser.getText().toString(), editTextPassword.getText().toString(), new LoginCallback() {
                 Intent intent ;
                 @Override
                 public void onSuccess(String rol) {

                     if(!rol.equalsIgnoreCase("Credenciales inválidas")){
                         switch (rol) {
                             case "directivo":
                                 intent = new Intent(MainActivity.this, DirectivoActivity.class);
                                 break;
                             case "atleta":
                                 intent = new Intent(MainActivity.this, AtletaActivity.class);
                                 break;
                             case "entrenador":
                                 intent = new Intent(MainActivity.this, EntrenadorActivity.class);
                                 break;

                         }
                         startActivity(intent);

                     }else{
                         Toast.makeText(MainActivity.this, rol, Toast.LENGTH_SHORT).show();
                     }

                 }

                 @Override
                 public void onFailure(String errorMessage) {
                     Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                 }
             });


            }else{
                Toast.makeText(this, "Debes de rellenar los campos", Toast.LENGTH_SHORT).show();
            }




        }

    }
  // directivo
 // qparker@example.com
 // M5kiW8Ludp
    // entrenador
 // crist.cristal@example.org
 // KyH9AEReSW
    // atleta
    // arobel@example.org
    // tkqrj0v68c

}
