package com.example.MyClub.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Controlers.UserControler;
import com.example.MyClub.Directivo.DirectivoActivity;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.MyClub.Interfaces.UserControllerCallback;
import com.example.MyClub.Models.User;
import com.example.MyClub.Windows.DialogWindows;
import com.example.conectarapi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userName, firstSurname, secondSurname, category, phoneNumber, email, weight, height, dayOfBirth, password;
    TextView txtWeight, txtHeight, txtDayOfBirth, txtCategory;
    Button btnSaveChanges,btnStart,btnBack;

    CheckBox checkBox_show_passwor;
    private static final String TAG = "EditUserActivity";

    User user;
    UserControler userControler = new UserControler();

    Intent intent;
    String[] originalValues;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    DialogWindows dialogWindows = new DialogWindows();


    String userRol;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_edit_user);
        String directivo = getString(R.string.directivo);
        initializeViews();
        listeners();
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        userRol = sharedPreferences.getString("rol", "no_user");

        if (user == null) {
            Toast.makeText(this, getResources().getString(R.string.null_user), Toast.LENGTH_SHORT).show();
        } else {
            if (user.getRol().equalsIgnoreCase(directivo) || userRol.equalsIgnoreCase(directivo)) {
                hideFields();
            }
            fillFields(user);
            saveOriginalValues(user);

        }


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
        checkBox_show_passwor = findViewById(R.id.check_box_show_password);
        btnSaveChanges = findViewById(R.id.btn_save_changes);
        btnStart = findViewById(R.id.btn_start);
        btnBack = findViewById(R.id.btn_back);



    }

    private void hideFields() {

        // Ocultar campos relacionados con dayOfBirth
        dayOfBirth.setVisibility(View.GONE);
        txtDayOfBirth.setVisibility(View.GONE);

        // Ocultar campos relacionados con category
        category.setVisibility(View.GONE);
        txtCategory.setVisibility(View.GONE);

        // Ocultar campos relacionados con weight
        weight.setVisibility(View.GONE);
        txtWeight.setVisibility(View.GONE);

        // Ocultar campos relacionados con height
        height.setVisibility(View.GONE);
        txtHeight.setVisibility(View.GONE);
    }

    public void fillFields(@NonNull User user) {
        userName.setText(user.getName());
        firstSurname.setText(user.getApellido());
        secondSurname.setText(user.getApellidosegundo());
        phoneNumber.setText(user.getTelefono());
        weight.setText(String.valueOf(user.getPeso()));
        height.setText(String.valueOf(user.getAltura()));
        email.setText(user.getEmail());
        dayOfBirth.setText(sdf.format(user.getFechaNacimiento()));
        password.setText(user.getPassword());
        category.setText(user.getCategoria());

    }

    public void listeners() {
        checkBox_show_passwor.setOnClickListener(EditUserActivity.this);
        btnSaveChanges.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnStart.setOnClickListener(this);
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
            if (user != null) {

                if (anyFieldChanged()){
                    dialogWindows.acceptCancelWindow(this, getResources().getString(R.string.titulo_guardar_cambios), getResources().getString(R.string.texto_guardar_cambios), new DialogListener() {
                        @Override
                        public void onApceptSelected() {
                            user.setName(userName.getText().toString());
                            user.setApellido(firstSurname.getText().toString());
                            user.setApellidosegundo(secondSurname.getText().toString());
                            user.setCategoria(category.getText().toString());
                            user.setTelefono(phoneNumber.getText().toString());
                            user.setEmail(email.getText().toString());
                            user.setPassword(password.getText().toString());

                            try{
                                user.setPeso(Float.parseFloat(weight.getText().toString()));

                            }catch (NumberFormatException e){
                                user.setPeso(0f);
                            }
                            try{
                                user.setAltura(Float.parseFloat(height.getText().toString()));
                            }catch (NumberFormatException e){
                                user.setAltura(0f);
                            }

                            try {
                                user.setFechaNacimiento(sdf.parse(dayOfBirth.getText().toString()));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            userControler.updateUser(user.getId(), user, new UserControllerCallback() {
                                @Override
                                public void onSucces(String succes) {
                                    fillFields(user);
                                    Toast.makeText(EditUserActivity.this, succes, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(String errorMessage) {
                                    Toast.makeText(EditUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                                }
                            }, EditUserActivity.this);




                        }

                        @Override
                        public void onCancelSelected() {
                            Toast.makeText(EditUserActivity.this, getResources().getString(R.string.cancel_changes), Toast.LENGTH_SHORT).show();
                            fillFields(user);

                        }
                    });

                }else{
                    Toast.makeText(this, getResources().getString(R.string.warning_changes), Toast.LENGTH_SHORT).show();
                }


            }
        } else if (v==btnStart) {
            saveOriginalValues(user);
            if(anyFieldChanged()){
                dialogWindows.acceptCancelWindow(this, getResources().getString(R.string.no_saved_changes_title), getResources().getString(R.string.no_saved_changes_q), new DialogListener() {
                    @Override
                    public void onApceptSelected() {
                        Intent intent =new Intent(EditUserActivity.this, DirectivoActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelSelected() {

                    }
                });


            }else{
                Intent intent =new Intent(EditUserActivity.this, DirectivoActivity.class);
                startActivity(intent);
            }




        } else if (v==btnBack) {
            saveOriginalValues(user);
            if(anyFieldChanged()){
                dialogWindows.acceptCancelWindow(this, getResources().getString(R.string.no_saved_changes_title), getResources().getString(R.string.no_saved_changes_q), new DialogListener() {
                    @Override
                    public void onApceptSelected() {
                        intent = new Intent(EditUserActivity.this, ListUserActivity.class);
                        intent.putExtra("ApiService",userRol);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelSelected() {

                    }
                });


            }else{
                intent = new Intent(EditUserActivity.this, ListUserActivity.class);
                intent.putExtra("ApiService",userRol);
                startActivity(intent);

            }




        }

    }

    // Metodo para guardar los valores originales del user y luego comparar si existe algun cambio;
    private void saveOriginalValues(User user) {
        originalValues = new String[]{
                user.getName(),
                user.getApellido(),
                user.getApellidosegundo(),
                user.getTelefono(),
                String.valueOf(user.getPeso()),
                String.valueOf(user.getAltura()),
                user.getEmail(),
                String.valueOf(sdf.format(user.getFechaNacimiento())),
                user.getPassword(),
                user.getCategoria()
        };
    }
    // Obtiene el valor actual de un EditText según su índice en la lista
    private String getEditTextValue(int index) {
        switch (index) {
            case 0: return userName.getText().toString();
            case 1: return firstSurname.getText().toString();
            case 2: return secondSurname.getText().toString();
            case 3: return phoneNumber.getText().toString();
            case 4: return weight.getText().toString();
            case 5: return height.getText().toString();
            case 6: return email.getText().toString();
            case 7: return dayOfBirth.getText().toString();
            case 8: return password.getText().toString();
            case 9: return category.getText().toString();
            // Agrega más casos para otros EditText aquí
            default: return "";

        }
    }
    // Compara los campos que tiene el objeto almacenados en una lista contra los campos que tienen los editText que se almacenaron en el metodo en un swwitch
    private boolean anyFieldChanged() {
        // Verifica si algún campo ha cambiado comparando los valores actuales con los valores originales
        for (int i = 0; i < originalValues.length; i++) {
            if (!((getEditTextValue(i) == null ? "" : getEditTextValue(i))
                    .equals(originalValues[i] == null ? "" : originalValues[i]))) {
                return true; // Al menos un campo ha cambiado
            }
        }
        return false; // Ningún campo ha cambiado
    }




}