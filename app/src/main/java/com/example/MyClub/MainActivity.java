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

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Controlers.ViewsController;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.LoginCallback;
import com.example.conectarapi.R;

import com.example.MyClub.Controlers.UserControler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Elementos para la pantalla principal
    EditText editTextUser;
    EditText editTextPassword;

    Button buttonLogin;
    UserControler userControler;

    CheckBox checkBox,checkBoxShowPassword;

    String passwordSaved;
    DialogWindow dialogWindow;

    ViewsController viewsController;


    String rol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        identifier();
        listeners();
        userControler = new UserControler(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        passwordSaved = sharedPreferences.getString("log_activado", getResources().getString(R.string.no));
        Toast.makeText(this, rol, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, passwordSaved, Toast.LENGTH_SHORT).show();
        dialogWindow = new DialogWindow();

        if (!passwordSaved.equalsIgnoreCase(getResources().getString(R.string.no))) {
            rol = sharedPreferences.getString("rol", "no_rol");
            if (!rol.equalsIgnoreCase("no_rol")) {
                startSesionWithSavedPassword(rol);
            }

        }

    }


    public void identifier() {
        editTextUser = findViewById(R.id.edit_text_user);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.check_box_remeber_password);
        checkBoxShowPassword = findViewById(R.id.check_box_show_password_login);
    }

    public void listeners() {
        buttonLogin.setOnClickListener(this);
        checkBoxShowPassword.setOnClickListener(this);
        checkBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (v == buttonLogin) {
            if (!editTextUser.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()) {


                userControler.login(editTextUser.getText().toString(), editTextPassword.getText().toString(), new LoginCallback() {
                    Intent intent;

                    @Override
                    public void onSuccess(String rol, int userId) {
                        Toast.makeText(MainActivity.this, String.valueOf(userId), Toast.LENGTH_SHORT).show();
                        if (userId>0) {
                            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("userId", userId);
                            editor.putString("rol", rol);
                            Constantes.setUserRol(rol);
                            Constantes.setUserId(userId);
                            if (checkBox.isChecked()) {
                                editor.putString("log_activado", "si");
                            }
                            editor.apply(); // o editor.commit();
                            viewsController = new ViewsController(MainActivity.this,rol,MainActivity.this);
                            viewsController.backToMain();
                            finish();

                        } else {
                            dialogWindow.infoWindow(MainActivity.this, getString(R.string.warnig),rol);
                        }

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(MainActivity.this, "Caca", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    }
                });


            } else {
                Toast.makeText(this, R.string.debes_de_rellenar_los_campos, Toast.LENGTH_SHORT).show();
            }


        }else if(v == checkBoxShowPassword){
            if(checkBoxShowPassword.isChecked()){
                editTextPassword.setInputType(1);
            }else{
                editTextPassword.setInputType(129);
            }
        }

    }

    public void startSesionWithSavedPassword(String rol) {
        viewsController = new ViewsController(MainActivity.this,rol,MainActivity.this);
        viewsController.backToMain();
        finish();

    }
}
