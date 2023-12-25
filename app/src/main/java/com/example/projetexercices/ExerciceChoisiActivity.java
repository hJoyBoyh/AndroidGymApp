package com.example.projetexercices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class ExerciceChoisiActivity extends AppCompatActivity {

    private TextView txtTitre,txtDescription,txtMuscle,txtExecution;
    private Button retour,delete,update;
    private int compteurMuscle = 0 ;
    int compteurDescription = 0 ;
    int compteurExecution = 0 ;

    TableLayout tableLayoutExecution;
    TableRow tDescription,tMuscle,tExecution;
    CardView cardExecution;

    private ImageView displayDescription, displayMuscle,displayExecution;

    private YouTubePlayerView yt;
    private ExerciceModel exerciceModelChoisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_choisi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDescription = findViewById(R.id.txtDescriptionExercice);
        txtMuscle = findViewById(R.id.txtMuscleExercice);
        txtExecution = findViewById(R.id.txtExecutionExercice);


        yt = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(yt);
        yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
              //  super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(exerciceModelChoisi.getLien() ,0);
            }
        });


        displayDescription = findViewById(R.id.btnDisplaytxtDescription);
        displayMuscle = findViewById(R.id.btnDisplaytxtMuscle);
        displayExecution = findViewById(R.id.btnDisplaytxtExecution);

        tDescription = findViewById(R.id.tableRowDescription);
        tMuscle = findViewById(R.id.tableRowMuscle);
        tExecution = findViewById(R.id.tableRowExecution);

        cardExecution = findViewById(R.id.cardViewExecution);
        tableLayoutExecution = findViewById(R.id.tableLayoutExecution);




        displayMuscle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    displayTxt("muscle");
                }
            });
        displayDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTxt("description");
            }
        });
        displayExecution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTxt("execution");
            }
        });




        // prendre la donnee de la bd ce qui est le exercice choisis
        Bundle extra = getIntent().getExtras();
        exerciceModelChoisi = extra.getParcelable(ExerciceModel.EXTRA);

        // Set title bar
        this.setTitle(exerciceModelChoisi.getTitle());

        // mettre les donnee de lexercice dans les txtview

        txtDescription.setText( exerciceModelChoisi.getDescription());
        txtMuscle.setText( exerciceModelChoisi.getMuscle());
        txtExecution.setText( exerciceModelChoisi.getExecution()    );



    }

   private void displayTxt(String displayChoice){


        if(displayChoice.equalsIgnoreCase("muscle")){

                  compteurMuscle++;
                  if(compteurMuscle == 1) {
                      txtMuscle.setVisibility(View.VISIBLE);
                  }
                  if(compteurMuscle == 2) {
                        txtMuscle.setVisibility(View.GONE);
                        compteurMuscle = 0;
                    }

            }

       if(displayChoice.equalsIgnoreCase("description")){


                   compteurDescription++;
                   if(compteurDescription == 1) {
                       txtDescription.setVisibility(View.VISIBLE);
                   }
                   if(compteurDescription == 2) {
                       txtDescription.setVisibility(View.GONE);
                       compteurDescription= 0;
                   }
               }


       if(displayChoice.equalsIgnoreCase("execution")){

                   compteurExecution++;
                   if(compteurExecution == 1) {
                       txtExecution.setVisibility(View.VISIBLE);

                       ViewGroup.LayoutParams cLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
                   }
                   if(compteurExecution == 2) {
                       txtExecution.setVisibility(View.GONE);
                       compteurExecution = 0;
                   }
               }


    }
}