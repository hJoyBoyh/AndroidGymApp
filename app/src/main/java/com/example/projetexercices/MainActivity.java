package com.example.projetexercices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView bicepsTxt;
    private TableLayout table;

    private ListView listeView;
    private  ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // variable initialise
        table = findViewById(R.id.LinearId);

        // appel de la base de donnee
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);

        //Inserer et enlever tous les data prefait
       //db.deleteAll();
        // db.addOneTest();
        //db.AllDataInsert();

        list = db.getNamesCategorie();

        createPanel();

    }
    void createPanel(){

        int compteur = 0;
        int idTemp = 0;

        for (int i = 0; i < list.size(); i++) {
        if(compteur == 0) {
            // creation table row
            idTemp =  View.generateViewId();
            TableRow tb = new TableRow(this);
            tb.setId(idTemp);


            // creation linearlayout
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
           layoutParams.setMargins(130,130,130,130);
           linearLayout.setLayoutParams(layoutParams);

           // creation cardview
            CardView cardView = new CardView(this);
           // ViewGroup.LayoutParams layoutParams1 = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // creation textview
            TextView textView = new TextView(this);
            textView.setText(list.get(i).toString());
            textView.setGravity(Gravity.CENTER);

            // creation imageview
            ImageView imageView = new ImageView(this);
            //chercher l image correspondant
            String imageName = list.get(i).toString().toLowerCase(); // store only image name in database(means not   store "R.drawable.image") like "image".
            int id = getResources().getIdentifier(this.getPackageName()+":drawable/" + imageName, null, null);
            imageView.setImageResource(id);


            //
            // arrangement du imageview
            LinearLayout.LayoutParams layoutParamsimg = new LinearLayout.LayoutParams(400, 400);
            layoutParamsimg.gravity =1;
            imageView.setLayoutParams(layoutParamsimg);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // ajout image et text dans le linear layout qui est dans le card
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            //mettre le liearn view dans le card view
            cardView.addView(linearLayout);

            // mettre le cardview dans le tablerow
            tb.addView(cardView);
            // mettre le table row dans le tablelayout
            table.addView(tb);
            table.setStretchAllColumns(true);
            compteur++;

            // action aller dans les exercice du categorie choisi
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToExerciceChoisiActivity(textView.getText().toString());
                }
            });


        }else if(compteur == 1){
            TableRow tb = findViewById(idTemp);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(130,130,130,130);
            linearLayout.setLayoutParams(layoutParams);

            CardView cardView = new CardView(this);

            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(list.get(i).toString());
            ImageView imageView = new ImageView(this);

            String imageName = list.get(i).toString().toLowerCase(); // store only image name in database(means not   store "R.drawable.image") like "image".
            int id = getResources().getIdentifier(this.getPackageName()+":drawable/" + imageName, null, null);
            imageView.setImageResource(id);

            //imageView.setImageResource(R.drawable.biceps);
            LinearLayout.LayoutParams layoutParamsimg = new LinearLayout.LayoutParams(400, 400);
            layoutParamsimg.gravity =1;
            imageView.setLayoutParams(layoutParamsimg);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            cardView.addView(linearLayout);


            tb.addView(cardView);

            compteur++;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToExerciceChoisiActivity(textView.getText().toString());
                }
            });

        }else if(compteur == 2){
            idTemp =  View.generateViewId();
            TableRow tb = new TableRow(this);
            tb.setId(idTemp);


            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(130,130,130,130);
            linearLayout.setLayoutParams(layoutParams);

            CardView cardView = new CardView(this);

            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(list.get(i).toString());
            ImageView imageView = new ImageView(this);

            String imageName = list.get(i).toString().toLowerCase(); // store only image name in database(means not   store "R.drawable.image") like "image".
            int id = getResources().getIdentifier(this.getPackageName()+":drawable/" + imageName, null, null);
            imageView.setImageResource(id);


            LinearLayout.LayoutParams layoutParamsimg = new LinearLayout.LayoutParams(400, 400);
            layoutParamsimg.gravity =1;
            imageView.setLayoutParams(layoutParamsimg);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            cardView.addView(linearLayout);


            tb.addView(cardView);
            table.addView(tb);
            table.setStretchAllColumns(true);
            compteur++;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToExerciceChoisiActivity(textView.getText().toString());
                }
            });

        }else if(compteur == 3){
            TableRow tb = findViewById(idTemp);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(130,130,130,130);
            linearLayout.setLayoutParams(layoutParams);

            CardView cardView = new CardView(this);

            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(list.get(i).toString());
            ImageView imageView = new ImageView(this);

            String imageName = list.get(i).toString().toLowerCase(); // store only image name in database(means not   store "R.drawable.image") like "image".
            int id = getResources().getIdentifier(this.getPackageName()+":drawable/" + imageName, null, null);
            imageView.setImageResource(id);


            LinearLayout.LayoutParams layoutParamsimg = new LinearLayout.LayoutParams(400, 400);
            layoutParamsimg.gravity =1;
            imageView.setLayoutParams(layoutParamsimg);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            cardView.addView(linearLayout);


            tb.addView(cardView);

            compteur = 0;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToExerciceChoisiActivity(textView.getText().toString());
                }
            });

        }




        }


    }

    void goToExerciceChoisiActivity(String categorie){
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        // list qui recup tout les exercice avec categorie biceps
        List<ExerciceModel> allExercice = db.getAllWithCategorie(categorie);

        // entree tout les donne de la liste pour le mettre en array list pour le send dans le intent
        ArrayList<ExerciceModel> arrayListAllExercice = (ArrayList<ExerciceModel>) allExercice;

        //intent
        Intent intent = new Intent(MainActivity.this,CategorieChosisActivity.class);

        //envoyer les donne
        intent.putParcelableArrayListExtra(ExerciceModel.EXTRA,arrayListAllExercice);
        //start le intent avec les donner envoyer
        startActivity(intent);

    }
}