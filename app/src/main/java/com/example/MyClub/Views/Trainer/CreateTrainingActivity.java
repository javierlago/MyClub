package com.example.MyClub.Views.Trainer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Adapters.TrainingAdapter;
import com.example.MyClub.Controlers.PostController;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.CallbackController;
import com.example.MyClub.Models.Exercice;
import com.example.MyClub.Models.Post;
import com.example.conectarapi.R;

import java.util.ArrayList;
import java.util.Date;

public class CreateTrainingActivity extends AppCompatActivity {


    Button btnAddExercise, btnSaveTraining;
    ImageButton btnCleanTraining,btnCleanExercise;

    EditText  etExerciseName, etExerciseSeries, etUnitsNumber, etUnitsType, etExerciseIntensity;

    ArrayList<Exercice> exercices = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();

    TrainingAdapter trainingAdapter;

    DialogWindow dialogWindow = new DialogWindow();

    RecyclerView recyclerViewExercises;
    PostController postController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_training_activity);
        DialogWindow dialogWindow = new DialogWindow();
        initViews();
        listeners();
        trainingAdapter = new TrainingAdapter(exercices, this);
        recyclerViewExercises.setAdapter(trainingAdapter);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
        postController = new PostController(this);
    }
    public void initViews() {
        btnAddExercise = findViewById(R.id.btn_add_exercise);
        btnSaveTraining = findViewById(R.id.btn_save_training);
        btnCleanTraining = findViewById(R.id.btn_post_clean);
        btnCleanExercise = findViewById(R.id.btn_post_clean_exercise);
        etExerciseName = findViewById(R.id.et_exercise_name);
        etUnitsType = findViewById(R.id.et_units_type);
        etUnitsNumber = findViewById(R.id.et_units_number);
        etExerciseIntensity = findViewById(R.id.et_intensity);
        etExerciseSeries = findViewById(R.id.et_series);
        recyclerViewExercises = findViewById(R.id.recyclerView_exercises);
    }
    /*
    * Method to fill the array with the EditTexts
    * */
    public void fillArrayEditText (ArrayList<EditText> editTexts) {
        editTexts.add(etExerciseName);
        editTexts.add(etExerciseSeries);
        editTexts.add(etUnitsNumber);
        editTexts.add(etUnitsType);
        editTexts.add(etExerciseIntensity);
    }
    /*
    * Method to check if the fields are empty
    */
    public boolean checkEmptyFields(ArrayList<EditText> editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public void cleanFields(ArrayList<EditText> editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
    }


    public void listeners() {
        btnAddExercise.setOnClickListener(v -> {
            fillArrayEditText(editTexts);
            if(!checkEmptyFields(editTexts)) {
                dialogWindow.infoWindow(this, getResources().getString(R.string.warnig), getResources().getString(R.string.empty_fields));
            }else{
                Exercice exercice = new Exercice();
                exercice.setNombre(etExerciseName.getText().toString());
                exercice.setSeries(Integer.parseInt(etExerciseSeries.getText().toString()));
                exercice.setUnidades(Integer.parseInt(etUnitsNumber.getText().toString()));
                exercice.setDescripcionUnidades(etUnitsType.getText().toString());
                exercice.setIntensidad(etExerciseIntensity.getText().toString());
                exercices.add(exercice);
                trainingAdapter.notifyDataSetChanged();
                cleanFields(editTexts);
            }
        });
        btnSaveTraining.setOnClickListener(v -> {
            if(exercices.isEmpty()){
                dialogWindow.infoWindow(this, getResources().getString(R.string.warnig), getResources().getString(R.string.no_exercises));
            }else{
                Post post = new Post(getResources().getString(R.string.training), exercices,new Date());
                postController.createPostWhithExercises(post,  new CallbackController() {
                    @Override
                    public void onSucces(String succes) {
                        dialogWindow.infoWindow(CreateTrainingActivity.this, getResources().getString(R.string.warnig), succes);
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                    dialogWindow.infoWindow(CreateTrainingActivity.this, getResources().getString(R.string.warnig), errorMessage);
                    }
                });

            }

        });
        btnCleanTraining.setOnClickListener(v -> {
            // Clean training
        });
        btnCleanExercise.setOnClickListener(v -> {
            // Clean exercise
        });
    }

}