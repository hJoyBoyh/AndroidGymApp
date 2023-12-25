package com.example.projetexercices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategorieChosisActivity extends AppCompatActivity {
    private Button retour;
    private ListView listViewCategorieChoisi;

    private FloatingActionButton addExerciceBtn;
    private ArrayList<ExerciceModel> listExercice;

    private ArrayList<String> exercicesId,exercicesImg,exercicesTitre,exercicesDescription;


    CustomAdapterBaseCategorieChoisis customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_chosis);

        // pour retourner a lactivity precedant
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // variable initialise

        listViewCategorieChoisi = findViewById(R.id.listViewCategorieChoisiId);
       addExerciceBtn = findViewById(R.id.btnAddNewExercice);

        // prendre les donnee de la bd
        Bundle extra = getIntent().getExtras();
        listExercice = extra.getParcelableArrayList(ExerciceModel.EXTRA);

        // SET LE TITRE DANS LE BAR
        this.setTitle(listExercice.get(0).getCategorie());

        // custom adapter

        exercicesTitre = new ArrayList<>();
        exercicesDescription = new ArrayList<>();
        exercicesId = new ArrayList<>();
        exercicesImg = new ArrayList<>();
        for (ExerciceModel exercices:listExercice) {
            exercicesId.add(String.valueOf(exercices.getId()));
            exercicesTitre.add(exercices.getTitle());
            exercicesDescription.add(exercices.getDescription());
            exercicesImg.add(exercices.getImage());
        }
        customAdapter = new CustomAdapterBaseCategorieChoisis
                (CategorieChosisActivity.this,
                        CategorieChosisActivity.this,exercicesId,exercicesImg,exercicesTitre,exercicesDescription,listExercice);

        // un arrayadapter pour la list en mettant les donner recu de la mainactivity
        //  ArrayAdapter customArrayAdapter = new ArrayAdapter<ExerciceModel>(CategorieChosisActivity.this,
         //  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listExercice);

          //set le adapter
         listViewCategorieChoisi.setAdapter(customAdapter);

         /*listViewCategorieChoisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 Toast.makeText(CategorieChosisActivity.this, "jajaj", Toast.LENGTH_SHORT).show();
                 ExerciceModel exerciceChosi = listExercice.get(i);

                 Intent intent = new Intent(CategorieChosisActivity.this,ExerciceChoisiActivity.class);
                 intent.putExtra(ExerciceModel.EXTRA,exerciceChosi);
                 startActivity(intent);
                 Toast.makeText(CategorieChosisActivity.this, exerciceChosi.toString(), Toast.LENGTH_SHORT).show();
             }
         });*/


         // add un exercice
        addExerciceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorieChosisActivity.this,AddExerciceActivity.class);
                finish();
                startActivity(intent);
            }
        });







        // retour mainActivity

    }
}