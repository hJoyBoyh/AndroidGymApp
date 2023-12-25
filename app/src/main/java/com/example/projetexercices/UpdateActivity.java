package com.example.projetexercices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText txtTitle,txtDescrition,txtMuscle,txtExecution,txtLien;

    RadioGroup rbtnCategorieChoisi;
    RadioButton radioButton;

    Button updateExercices;


    private ExerciceModel exerciceModelChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtTitle = findViewById(R.id.txtTitreUpdate);
        txtDescrition = findViewById(R.id.txtDescriptionUpdate);
        txtMuscle = findViewById(R.id.txtMuscleUpdate);
        txtExecution = findViewById(R.id.txtExecutionUpdate);
        txtLien = findViewById(R.id.txtLienUpdate);

        rbtnCategorieChoisi = findViewById(R.id.radioGroupUpdate);

        updateExercices = findViewById(R.id.btnUpdateExercice);

        // prendre la donnee de la bd ce qui est le exercice choisis
        Bundle extra = getIntent().getExtras();
        exerciceModelChoisi = extra.getParcelable(ExerciceModel.EXTRA);

        txtTitle.setText(exerciceModelChoisi.getTitle());
        txtDescrition.setText(exerciceModelChoisi.getDescription());
        txtMuscle.setText(exerciceModelChoisi.getMuscle());
        txtExecution.setText(exerciceModelChoisi.getExecution());
        txtLien.setText(exerciceModelChoisi.getLien());

        // set la categorie
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Biceps")){
                radioButton = findViewById(R.id.rbtnBicepsUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnBicepsUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Triceps")){
            radioButton = findViewById(R.id.rbtnTricepsUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnTricepsUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Dos")){
            radioButton = findViewById(R.id.rbtnDosUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnDosUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Epaules")){
            radioButton = findViewById(R.id.rbtnEpaulesUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnEpaulesUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Cuisses")){
            radioButton = findViewById(R.id.rbtnCuissesUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnCuissesUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Quadriceps")){
            radioButton = findViewById(R.id.rbtnQuadricepsUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnQuadricepsUpdate);
        }
        if(exerciceModelChoisi.getCategorie().equalsIgnoreCase("Ischios")){
            radioButton = findViewById(R.id.rbtnIschiosUpdate);
            rbtnCategorieChoisi.check(R.id.rbtnIschiosUpdate);
        }


        updateExercices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper db = new DataBaseHelper(UpdateActivity.this);

                db.updateExercice(exerciceModelChoisi.getId(),
                        txtTitle.getText().toString(),
                        txtDescrition.getText().toString(),
                        txtMuscle.getText().toString(),
                        txtExecution.getText().toString(),
                        exerciceModelChoisi.getImage(),
                        txtLien.getText().toString(),
                        radioButton.getText().toString());
                finish();
            }
        });

    }
}