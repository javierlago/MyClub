package com.example.MyClub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conectarapi.R;

import controlers.UserControler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Elementos para la pantalla principal
    EditText editTextUser;
    EditText editTextPassword;

    Button buttonLogin;
    UserControler userControler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserControler userControler = new UserControler();
        identifier();
        listeners();


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
        userControler = new UserControler();
        if(v==buttonLogin){
            if(!editTextUser.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){
            userControler.login(this,editTextUser.getText().toString(),editTextPassword.getText().toString());
            }else{
                Toast.makeText(this, "Debes de rellenar los campos", Toast.LENGTH_SHORT).show();
            }




        }

    }
}
