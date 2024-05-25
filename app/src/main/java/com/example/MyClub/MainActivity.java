package com.example.MyClub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Constantes.Constantes;
import com.example.MyClub.Interfaces.LoginCallback;
import com.example.MyClub.Atleta.AtletaActivity;
import com.example.MyClub.Directivo.DirectivoActivity;
import com.example.MyClub.Entrenador.EntrenadorActivity;
import com.example.conectarapi.R;

import com.example.MyClub.Controlers.UserControler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Elementos para la pantalla principal
    EditText editTextUser;
    EditText editTextPassword;

    Button buttonLogin;
    UserControler userControler;

    CheckBox checkBox;

    String passwordSaved;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identifier();
        listeners();
        userControler  = new UserControler(this);
        SharedPreferences sharedPreferences=  getSharedPreferences("MyAppPrefs",Context.MODE_PRIVATE);
        passwordSaved = sharedPreferences.getString("log_activado",null);
        if(passwordSaved != null){
            String rol = sharedPreferences.getString("rol",null);
            if(rol != null){
            startSesionWithSavedPassword(rol);
            }

        }

    }




    public void identifier(){
        editTextUser = findViewById(R.id.edit_text_user);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.check_box_remeber_password);
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
                 public void onSuccess(String rol,int userId) {

                     if(!rol.equalsIgnoreCase(getResources().getString(R.string.credenciales_invalidas))){


                         SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                         SharedPreferences.Editor editor = sharedPreferences.edit();
                         editor.putInt("userId", userId);

                         if(checkBox.isChecked()){
                             editor.putString("rol",rol);
                             editor.putString("log_activado","si");

                         }
                         editor.apply(); // o editor.commit();



                         if (Constantes.getDirectivo(MainActivity.this).equalsIgnoreCase(rol)) {
                             intent = new Intent(MainActivity.this, DirectivoActivity.class);
                         } else if (Constantes.getAtleta(MainActivity.this).equalsIgnoreCase(rol)) {
                             intent = new Intent(MainActivity.this, AtletaActivity.class);
                         } else if (Constantes.getEntrenador(MainActivity.this).equalsIgnoreCase(rol)) {
                             intent = new Intent(MainActivity.this, EntrenadorActivity.class);
                         }

                         startActivity(intent);
                         finish();

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
                Toast.makeText(this, R.string.debes_de_rellenar_los_campos, Toast.LENGTH_SHORT).show();
            }




        }

    }
    public void startSesionWithSavedPassword(String rol){
        Intent intent = null;

        if (Constantes.getDirectivo(MainActivity.this).equalsIgnoreCase(rol)) {
            intent = new Intent(MainActivity.this, DirectivoActivity.class);
        } else if (Constantes.getAtleta(MainActivity.this).equalsIgnoreCase(rol)) {
            intent = new Intent(MainActivity.this, AtletaActivity.class);
        } else if (Constantes.getEntrenador(MainActivity.this).equalsIgnoreCase(rol)) {
            intent = new Intent(MainActivity.this, EntrenadorActivity.class);
        }

        startActivity(intent);
        finish();



    }
}
