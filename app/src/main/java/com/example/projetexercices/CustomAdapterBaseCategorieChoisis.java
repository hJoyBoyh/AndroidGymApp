package com.example.projetexercices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapterBaseCategorieChoisis extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater infliter;

    private ArrayList<String> exerciceId,exerciceImg, exerciceTitre, exerciceDescrition;
    //exerciceImg
    private ArrayList<ExerciceModel> listExercices;


    public CustomAdapterBaseCategorieChoisis(Context context, Activity activity,
                                              ArrayList exerciceId,ArrayList exerciceImg,
                                             ArrayList exerciceTitre, ArrayList exerciceDescrition,ArrayList listExercices) {
        this.context = context;
        this.activity = activity;

        this.exerciceId = exerciceId;
        this.exerciceImg = exerciceImg;

        this.exerciceTitre = exerciceTitre;
        this.exerciceDescrition = exerciceDescrition;
        this.listExercices =listExercices;
        infliter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return exerciceId.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       view =infliter.inflate(R.layout.row_categoriechoisi,null);

        TextView titre = view.findViewById(R.id.txtTitreExerciceFromCategorie);
        TextView description =view.findViewById(R.id.txtDescriptionExerciceFromCategorie);
        ImageButton update = view.findViewById(R.id.btnUpdateExerciceFromCategorie);
        ImageButton delete = view.findViewById(R.id.btnDeleteExerciceFromCategorie);
        CardView cardView = view.findViewById(R.id.cardView);

        ImageView img =  view.findViewById(R.id.imageViewExerciceFromCategorie);


        String imageName = exerciceImg.get(i).toString().toLowerCase(); // store only image name in database(means not   store "R.drawable.image") like "image".
        int id = context.getResources().getIdentifier(context.getPackageName()+":drawable/" + imageName, null, null);
        img.setImageResource(id);

        ConstraintLayout.LayoutParams layoutParamsimg = new ConstraintLayout.LayoutParams(300, 500);

        img.setLayoutParams(layoutParamsimg);
        img.setAdjustViewBounds(true);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        titre.setText(exerciceTitre.get(i).toString());
        description.setText(exerciceDescrition.get(i).toString());

        // update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ExerciceModel exerciceChosi = listExercices.get(i);


                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra(ExerciceModel.EXTRA,exerciceChosi);
                activity.finish();
                context.startActivity(intent);
            }
        });
        //delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper db = new DataBaseHelper(context);
                db.deleteExercice(listExercices.get(i).getId());

                System.exit(0);
            }
        });



        // onclick listener pour aller dans une autre activity
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciceModel exerciceChosi = listExercices.get(i);

                Intent intent = new Intent(context,ExerciceChoisiActivity.class);
                intent.putExtra(ExerciceModel.EXTRA,exerciceChosi);
                context.startActivity(intent);


            }
        });



        return view;
    }
}
