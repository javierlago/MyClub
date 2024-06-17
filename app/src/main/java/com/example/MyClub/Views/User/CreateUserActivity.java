package com.example.MyClub.Views.User;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Controlers.EditBarControler;
import com.example.MyClub.Controlers.UserControler;
import com.example.MyClub.Interfaces.CallbackController;
import com.example.MyClub.Models.User;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Utitities.Validations;
import com.example.conectarapi.R;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userName, firstSurname, secondSurname, category, phoneNumber, email, weight, height, dayOfBirth, password;
    TextView txtWeight, txtHeight, txtDayOfBirth, txtCategory, createUserTitle;
    ImageButton btnSaveChanges, btnStart, btnBack;
    CheckBox checkBox_show_passwor;
    String userRol;
    String userRolApi;
    Intent intent;
    DialogWindow dialogWindows = new DialogWindow();
    UserControler userController;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_activity);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        userRol = sharedPreferences.getString("rol", "no_user");
        intent = getIntent();
        userRolApi = intent.getStringExtra("ApiService");
        initializeViews();
        listeners();
        //Ocultamos los campos segun quien sea el usuario
        hideFields(userRolApi);
        // Cambiamos el titulo de la cabecera segun el usuario a crear
        changeHeader(userRolApi);
        checkBox_show_passwor.setChecked(true);
        userController = new UserControler(this);
    }

    private void initializeViews() {
        // Inicializar las vistas
        userName = findViewById(R.id.edit_text_name);
        firstSurname = findViewById(R.id.edit_txt_first_surname);
        secondSurname = findViewById(R.id.edti_txt_second_surname);
        phoneNumber = findViewById(R.id.edit_txt_number);
        weight = findViewById(R.id.edit_txt_weigth);
        email = findViewById(R.id.edit_txt_email);
        txtWeight = findViewById(R.id.txt_weigth);
        txtHeight = findViewById(R.id.txt_heigth);
        txtCategory = findViewById(R.id.txt_category);
        dayOfBirth = findViewById(R.id.edit_txt_date_of_birth);
        category = findViewById(R.id.edit_txt_category);
        weight = findViewById(R.id.edit_txt_weigth);
        txtWeight = findViewById(R.id.txt_weigth);
        height = findViewById(R.id.edit_txt_heigth);
        txtDayOfBirth = findViewById(R.id.txt_date_of_birth);
        password = findViewById(R.id.edit_txt_password);
        checkBox_show_passwor = findViewById(R.id.check_box_show_password_create_user);
        btnSaveChanges = findViewById(R.id.btn_save_changes);
        btnStart = findViewById(R.id.btn_start);
        btnBack = findViewById(R.id.btn_back);
        createUserTitle = findViewById(R.id.txt_create_user_title);

    }


    public void changeHeader(String userRolApi) {
        String header = createUserTitle.getText().toString();
        header = header + " " + userRolApi;
        createUserTitle.setText(header);
    }

    public void listeners() {
        checkBox_show_passwor.setOnClickListener(CreateUserActivity.this);
        btnSaveChanges.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        dayOfBirth.setOnClickListener(this);

    }

    public boolean setUser() {

        if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.directivo))) {
            Toast.makeText(CreateUserActivity.this, "Directivo", Toast.LENGTH_SHORT).show();
            return userName.getText().toString().isEmpty() || firstSurname.getText().toString().isEmpty() || secondSurname.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty();
        } else if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.entrenador))) {
            Toast.makeText(CreateUserActivity.this, "Entrenador", Toast.LENGTH_SHORT).show();
            return userName.getText().toString().isEmpty() || firstSurname.getText().toString().isEmpty() || secondSurname.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || category.getText().toString().isEmpty();
        } else if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.atleta))) {
            Toast.makeText(CreateUserActivity.this, "Atleta", Toast.LENGTH_SHORT).show();
            return userName.getText().toString().isEmpty() || firstSurname.getText().toString().isEmpty() || secondSurname.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || email.getText().toString().isEmpty() || weight.getText().toString().isEmpty() || height.getText().toString().isEmpty() || dayOfBirth.getText().toString().isEmpty() || password.getText().toString().isEmpty() || category.getText().toString().isEmpty();
        } else {
            return false;
        }
    }

    public void hideFields(String userRolApi) {
        if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.directivo))) {
            txtWeight.setVisibility(View.GONE);
            weight.setVisibility(View.GONE);
            txtHeight.setVisibility(View.GONE);
            height.setVisibility(View.GONE);
            txtDayOfBirth.setVisibility(View.GONE);
            dayOfBirth.setVisibility(View.GONE);
            txtCategory.setVisibility(View.GONE);
            category.setVisibility(View.GONE);
        } else if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.entrenador))) {
            txtWeight.setVisibility(View.GONE);
            weight.setVisibility(View.GONE);
            txtHeight.setVisibility(View.GONE);
            height.setVisibility(View.GONE);
            txtDayOfBirth.setVisibility(View.GONE);
            dayOfBirth.setVisibility(View.GONE);

        } else if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.atleta))) {
            if (userRol.equalsIgnoreCase(Constantes.getManager(this))) {
                txtWeight.setVisibility(View.GONE);
                weight.setVisibility(View.GONE);
                txtHeight.setVisibility(View.GONE);
                height.setVisibility(View.GONE);
            }
            txtDayOfBirth.setVisibility(View.GONE);
            dayOfBirth.setVisibility(View.GONE);
            txtCategory.setVisibility(View.GONE);
            category.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == checkBox_show_passwor) {
            if (checkBox_show_passwor.isChecked()) {
                // Show password
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                // Hide password
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        } else if (v == btnSaveChanges) {
            if (setUser()) {
                dialogWindows.infoWindow(CreateUserActivity.this, getResources().getString(R.string.warnig), getResources().getString(R.string.create_user_warning_message));
            } else if (!Validations.isValidEmail(email.getText().toString())) {
                dialogWindows.infoWindow(CreateUserActivity.this, getResources().getString(R.string.warnig), getResources().getString(R.string.email_warnign_text));
            } else if (!Validations.isValidPassword(password.getText().toString())) {
                dialogWindows.infoWindow(CreateUserActivity.this, getResources().getString(R.string.warnig), getResources().getString(R.string.create_user_warning_message_password));
            } else {
                userController.createUser(getUser(), new CallbackController() {
                    @Override
                    public void onSucces(String succes) {
                        Toast.makeText(CreateUserActivity.this, succes, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(CreateUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }


                }, this);

            }

        } else if (v == btnBack) {
            EditBarControler.onBackButton(setUser(), CreateUserActivity.this);
        } else if (v == btnStart) {
            EditBarControler.toStartButton(setUser(), CreateUserActivity.this, userRol, userRolApi);
        } else if (v == dayOfBirth) {
            dialogWindows.datePickerDialog(this, dayOfBirth, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Ajusta el mes, ya que en Java los meses comienzan en 0
                    monthOfYear = monthOfYear + 1;
                    // Formatea la fecha en el formato "dd-MM-yyyy"
                    @SuppressLint("DefaultLocale") String date = String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear, year);
                    // Establece la fecha en el EditText
                    dayOfBirth.setText(date);
                }
            });
        }

    }

    public User getUser() {
        User user = new User();
        user.setName(userName.getText().toString());
        user.setApellido(firstSurname.getText().toString());
        user.setApellidosegundo(secondSurname.getText().toString());
        user.setTelefono(phoneNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        if (userRolApi.equalsIgnoreCase(getResources().getString(R.string.atleta))) {
            user.setPeso(Float.parseFloat(weight.getText().toString()));
            user.setAltura(Float.parseFloat(height.getText().toString()));
            try {
                user.setFechaNacimiento(Date.valueOf(dayOfBirth.getText().toString()));
            } catch (IllegalArgumentException e) {
                user.setFechaNacimiento(null);
            }
            user.setCategoria(category.getText().toString());
        }
        user.setRol(userRolApi);
        return user;
    }


}