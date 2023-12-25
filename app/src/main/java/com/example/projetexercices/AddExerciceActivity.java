package com.example.projetexercices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddExerciceActivity extends AppCompatActivity {

    EditText txtTitle,txtDescrition,txtMuscle,txtExecution,txtLien;

    RadioGroup rbtnCategorieChoisi;
    RadioButton radioButton;

    Button addExercices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercice);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = findViewById(R.id.txtTitreAdd);
        txtDescrition = findViewById(R.id.txtDescriptionAdd);
        txtMuscle = findViewById(R.id.txtMuscleAdd);
        txtExecution = findViewById(R.id.txtExecutionAdd);
        txtLien = findViewById(R.id.txtLienAdd);

        rbtnCategorieChoisi = findViewById(R.id.radioGroupAdd);

        addExercices = findViewById(R.id.btnAddExercice);



        addExercices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get radio seletionner et le text contenue
                int radioButtonID = rbtnCategorieChoisi.getCheckedRadioButtonId();
                 radioButton = rbtnCategorieChoisi.findViewById(radioButtonID);



              DataBaseHelper db = new DataBaseHelper(AddExerciceActivity.this);
              db.addExercice(txtTitle.getText().toString(),
                      txtDescrition.getText().toString(),
                      txtMuscle.getText().toString(),
                      txtExecution.getText().toString(),txtLien.getText().toString(),
                      radioButton.getText().toString());

               // oublie pas qui faut refresh essaye de fermer tous les instancepuis de la reouvrir en chaine



              System.exit(0);


            }
        });




    }
}