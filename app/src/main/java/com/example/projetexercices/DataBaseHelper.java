package com.example.projetexercices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DATABASE_NAME = "ProjetExercice.db";
    private static final int DATABASE_VERSION =2;
    private static final String TABLE_NAME = "my_exercicesDB";
    private static final  String COLUMN_ID = "_id";
    private static final  String COLUMN_TITLE = "exercice_title";
    private static final  String COLUMN_DESCRIPTION = "exercice_description";
    private static final String COLUMN_MUSCLE = "exercice_muscle";
    private static final String COLUMN_EXECUTION = "exercice_execution";
    private static final String COLUMN_IMAGE = "exercice_image";
    private static final String COLUMN_LIEN = "exercice_lien";
    private static final String COLUMN_CATEGORIE = "exercice_categorie";

    public DataBaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query ="CREATE TABLE "+ TABLE_NAME+
                "("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE +" TEXT, "+
                COLUMN_DESCRIPTION + " TEXT, "+
                COLUMN_MUSCLE +" TEXT, "+
                COLUMN_EXECUTION +" TEXT, "+
                COLUMN_IMAGE +" TEXT, "+
                COLUMN_LIEN +" TEXT, "+
                COLUMN_CATEGORIE +" TEXT);";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //get all
    public List<ExerciceModel> getAll(){
        // list exercices
        List<ExerciceModel> listExercices = new ArrayList<>();

        String query = ("SELECT * FROM "+TABLE_NAME);
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor lire le query
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int exerciceId = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String muscle = cursor.getString(3);
                String execution = cursor.getString(4);
                String image = cursor.getString(5);
                String lien = cursor.getString(6);
                String categorie = cursor.getString(7);

                //instancier un exercice
                ExerciceModel exercice = new ExerciceModel(
                        exerciceId
                        ,title,description,muscle
                        ,execution,image,lien,categorie);
                //ajouter dans la list exercice
                listExercices.add(exercice);
            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();



        return listExercices;

    }

   public List<ExerciceModel> getAllWithCategorie(String categorie){

        List<ExerciceModel> exerciceWithCategorie = new ArrayList<>();


       String query = ("SELECT * FROM "+TABLE_NAME +" WHERE "+COLUMN_CATEGORIE+"='"+categorie+"'");

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(query,null);


       if(cursor.moveToFirst()){
           do {
               int exerciceId = cursor.getInt(0);
               String title = cursor.getString(1);
               String description = cursor.getString(2);
               String muscle = cursor.getString(3);
               String execution = cursor.getString(4);
               String image = cursor.getString(5);
               String lien = cursor.getString(6);
               String categ = cursor.getString(7);

               ExerciceModel exercice = new ExerciceModel(
                       exerciceId
                       ,title,description,muscle
                       ,execution,image,lien,categ);

               exerciceWithCategorie.add(exercice);
           }while(cursor.moveToNext());
       }else{

       }
       cursor.close();
       db.close();

       return exerciceWithCategorie;
    }

    public void addExercice(String title,String description,String muscle,
                       String execution,String lien, String categorie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESCRIPTION,description);
        cv.put(COLUMN_MUSCLE,muscle);
        cv.put(COLUMN_EXECUTION,execution);
        cv.put(COLUMN_IMAGE,"ic_launcher_background");
        cv.put(COLUMN_LIEN,lien);
        cv.put(COLUMN_CATEGORIE,categorie);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result ==-1){
            Toast.makeText(context,"failed enter data", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(context," add successe data", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteExercice(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = (COLUMN_ID+"="+id);
     long result=   db.delete(TABLE_NAME,whereClause,null);



        if(result ==-1){
            Toast.makeText(context,"failed delete data", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(context," delete success data", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateExercice(int id, String title,String description,String muscle,
                               String execution,String img,String lien,String categorie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESCRIPTION,description);
        cv.put(COLUMN_MUSCLE,muscle);
        cv.put(COLUMN_EXECUTION,execution);
        cv.put(COLUMN_LIEN,lien);
        cv.put(COLUMN_IMAGE,img);
        cv.put(COLUMN_CATEGORIE,categorie);

        String whereClause = (COLUMN_ID+"="+id);

       long result = db.update(TABLE_NAME,cv,whereClause,null);
        if(result ==-1){
            Toast.makeText(context,"failed update data", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(context," update success data", Toast.LENGTH_SHORT).show();


        }


    }
    public ArrayList<String>getNamesCategorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> namesCategorieList ;

        LinkedHashSet namesCategorieNoDuplicate = new LinkedHashSet();

        String query = ("SELECT * FROM "+TABLE_NAME);

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {

                String categ = cursor.getString(7);



               namesCategorieNoDuplicate.add(categ);
            }while(cursor.moveToNext());

        }else{

        }
        db.close();
        cursor.close();


        namesCategorieList = new ArrayList<>(namesCategorieNoDuplicate);

        return namesCategorieList;
    }

    void addOneTest(){
        // test de biceps
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, "Curlbarre");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation sollicite et " +
                        "développe les biceps. Le curl barre est l’exercice " +
                        "d’isolation de base pour les biceps.");

        values.put(COLUMN_MUSCLE, "Le biceps brachial, courte et " +
                "longue portion, le brachial antérieur et le long supinateur.");

        values.put(COLUMN_EXECUTION, "En position de départ debout, " +
                "le dos immobile et droit, les genoux fléchis ou une " +
                "jambe avancée pour éviter de tricher en s'aidant de l'élan e" +
                "t les coudes prés du corps. Monter et descendre la barre sans à-coups." +
                " Vous pouvez varier" +
                " l'écartement des mains en utilisant une prise large, moyenne ou serrée.");

        values.put(COLUMN_IMAGE, "Biceps");
        values.put(COLUMN_CATEGORIE, "Biceps");
        values.put(COLUMN_LIEN,
                                "ZXYkt-pkcAQ");

        long result =  db.insert(TABLE_NAME, null, values);
        if(result ==-1){
            Toast.makeText(context,"failed enter data", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(context," add successe data", Toast.LENGTH_SHORT).show();
        }
    }

    // methode pour delete juste une fois tout les donner dans le MainActivity
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }


    // methode pour inserer juste une fois tout les donner dans le MainActivity
    public void AllDataInsert()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //CURL BARRE
        values.put(COLUMN_TITLE, "Curlbarre");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation sollicite et " +
                        "développe les biceps. Le curl barre est l’exercice " +
                        "d’isolation de base pour les biceps.");

        values.put(COLUMN_MUSCLE, "Le biceps brachial, courte et " +
                "longue portion, le brachial antérieur et le long supinateur.");

        values.put(COLUMN_EXECUTION, "En position de départ debout, " +
                "le dos immobile et droit, les genoux fléchis ou une " +
                "jambe avancée pour éviter de tricher en s'aidant de l'élan e" +
                "t les coudes prés du corps. Monter et descendre la barre sans à-coups." +
                " Vous pouvez varier" +
                " l'écartement des mains en utilisant une prise large, moyenne ou serrée.");

        values.put(COLUMN_CATEGORIE, "Biceps");
        values.put(COLUMN_IMAGE,"_40_curl_barre");
        values.put(COLUMN_LIEN, "ZXYkt-pkcAQ");


        db.insert(TABLE_NAME, null, values);

        //CURL HALTERE

        values.put(COLUMN_TITLE, "Curl Haltere");
        values.put(COLUMN_DESCRIPTION,
                "Une alternative à la barre qui permet de travailler \n" +
                        "les bras séparément. " +
                        "Les haltères permettent pas " +
                        "mal de variantes intéressantes. " +
                        "Avec elles, " +
                        " vous allez vous forger des biceps ");

        values.put(COLUMN_MUSCLE, "Le biceps brachial, courte et longue portion, le brachial antérieur et le long supinateur.\n" +
                "Le brachial antérieur est utilisé " +
                "pendant l’exécution du curl avec haltère, " +
                "quel que soit la position de la main. Il se" +
                " trouve sous le biceps.\n" +
                "La rotation de poignet pendant le mouvement, appelée \"supination\" " +
                "(paume de la main vers soi), permet de plus solliciter le " +
                "biceps brachial.\n" +
                "Les curls marteaux (pouce vers le haut) " +
                "sollicitent quant à eux plus intensément " +
                "le long supinateur.");


        values.put(COLUMN_EXECUTION, "En position de départ, le dos collé contre \n" +
                "le banc ou assis à son extrémité, " +
                "les bras contre les flancs, \n" +
                "les pieds à plat sur le sol et " +
                "les genoux serrés pour éviter \n" +
                "que les haltères touchent les " +
                "cuisses. Amener \n" +
                "l'haltère au niveau de l'épaule " +
                "à la force des biceps, \n" +
                "en effectuant une rotation du " +
                "poignet. Les coudes ne doivent\n" +
                " pas dévier vers l'avant ou vers" +
                " l'arrière. Ne pas utiliser \n" +
                "l'élan pour soulever la charge,\n" +
                " ce sont seulement les avant-bras " +
                "doivent bouger.");
        values.put(COLUMN_IMAGE,"_40_curl_haltere");

        values.put(COLUMN_CATEGORIE, "Biceps");
        values.put(COLUMN_LIEN, "dh6Tcwy9a_o&t=1s");
        db.insert(TABLE_NAME, null, values);

        // CURL PUPITRE BARRE

        values.put(COLUMN_TITLE, "Curl Pupitre Barre");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice va faire travailler l\n" +
                        "es biceps avec une grande intensité. \n" +
                        "Le pupitre permet de se concentrer sur les muscles des \n" +
                        "bras et évite de tricher comme c'est souvent le cas avec les curls…");

        values.put(COLUMN_MUSCLE, "Le biceps brachial, courte et \n" +
                "longue portion, le brachial antérieur et le long supinateur.");


        values.put(COLUMN_EXECUTION, "Il est possible de réaliser cet exercice de musculation pour les biceps de différentes façons :\n" +
                "\n" +
                "AVEC UN HALTÈRE\n" +
                "Debout, un bras en appui sur un banc très incliné de 45 à 80°C, \n" +
                "bien en contact avec le dossier du coude jusqu'à l’épaule. Fléchir\n" +
                " l’avant-bras sans casser le poignet et ramener la charge vers l’épaule.\n" +
                " Redescendre ensuite l’haltère sans heurter le banc. Pensez à fléchir \n" +
                "légèrement les genoux et à garder le tronc bien immobile.\n" +
                "\n" +
                "AVEC UNE BARRE DROITE OU COUDÉE \"EZ\"\n" +
                "Tenir une barre coudée avec une prise plutôt large, \n" +
                "mains en supination (plus confortable), et les coudes un peu plus haut \n" +
                "pour mieux respirer. Monter et descendre la barre en évitant que les \n" +
                "bras soient complètement détendus en position basse (risque de tendinite).\n" +
                " Évitez de tricher en vous penchant d'avant en arrière pour remonter la barre par effet de levier, e\n" +
                "t pensez à garder les biceps contractés en position haute. \n" +
                "Un repose barre est nécessaire.");


        values.put(COLUMN_CATEGORIE, "Biceps");
        values.put(COLUMN_IMAGE,"_40_curl_barre");
        values.put(COLUMN_LIEN, "quZzNBrYBxw");


        db.insert(TABLE_NAME, null, values);


        // CURL CONCENTRATION
        values.put(COLUMN_TITLE, "Curl Concentration");
        values.put(COLUMN_DESCRIPTION,
                "\n" +
                        "Un exercice d'isolation avec haltère qui va vous permettre de peaufiner \n" +
                        "vos biceps. Il a la réputation de donner du « pic » au biceps. \n" +
                        "Tous les conseils pour une exécution parfaite…");


        values.put(COLUMN_MUSCLE, "Le biceps brachial, courte et longue portion, \n" +
                "le brachial antérieur et le long supinateur.");


        values.put(COLUMN_EXECUTION, "Assis sur un banc ou une chaise, \n" +
                "le dos droit et légèrement penché en avant, \n" +
                "le bras perpendiculaire au sol, le coude appuyé sur \n" +
                "l’intérieur de la cuisse près du genou et la main en pronation\n" +
                " qui tient l’haltère.");


        values.put(COLUMN_CATEGORIE, "Biceps");
        values.put(COLUMN_IMAGE,"_40_curl_concentration");
        values.put(COLUMN_LIEN, "AeHpcMxhqgI");
        db.insert(TABLE_NAME, null, values);


        // Dos
        //Tractions à la barre fixe
        values.put(COLUMN_TITLE, "Tractions à la barre fixe");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation \n" +
                        "àsollicite et développe \n" +
                        "les muscles du dos, \n" +
                        "surtout au niveau de la \n" +
                        "largeur. Les tractions à \n" +
                        "la barre fixe sont de \n" +
                        "formidables bâtisseuses \n" +
                        "de dorsaux. En tant que mouvement de base, \n" +
                        "nous vous conseillons de l'inclure dans \n" +
                        "votre routine de dos. Ceux qui ne peuvent \n" +
                        "pas faire de tractions avec le poids du \n" +
                        "corps pourront se diriger vers les tirages \n" +
                        "à la poulie haute.\n");


        values.put(COLUMN_MUSCLE, "Principalement le grand dorsal, \n" +
                "grand rond, petit rond et \n" +
                "secondairement les muscles \n" +
                "des bras (biceps brachial, brachial antérieur, long supinateur), \n" +
                "les trapèzes, rhomboïdes et deltoïdes postérieurs.");


        values.put(COLUMN_EXECUTION, "En position de départ, \n" +
                "vous êtes accroché à la barre, \n" +
                "les bras tendus avec une prise \n" +
                "plus grande que la largeur d'épaules et \n" +
                "les pieds croisés ou parallèles. \n" +
                "Monter en amenant le menton au \n" +
                "niveau de la barre et redescendre \n" +
                "sans à coup en gardant la tension dans les muscles.");



        values.put(COLUMN_CATEGORIE, "Dos");
        values.put(COLUMN_IMAGE,"intro_tirage_devant");
        values.put(COLUMN_LIEN,"OnKLnb2vsPI");
        db.insert(TABLE_NAME, null, values);


        //tirage potrine
        values.put(COLUMN_TITLE, "Tirage poitrine");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation \n" +
                        "sollicite les muscles \n" +
                        "du dos au niveau de la largeur. \n" +
                        "Le travail à la poulie haute \n" +
                        "permet à ceux qui ne sont pas \n" +
                        "encore capables de faire des tractions \n" +
                        "sur la barre fixe avec le poids du corps, \n" +
                        "d’exercer les dorsaux.\n");


        values.put(COLUMN_MUSCLE, "Principalement le grand dorsal,\n" +
                " grand rond, petit rond et secondairement \n" +
                "les muscles des bras \n" +
                "(biceps brachial, brachial antérieur, long supinateur), \n" +
                "les trapèzes, rhomboïdes et deltoïdes postérieurs. \n" +
                "Si le buste reste bien droit, \n" +
                "on sollicite la partie externe des dorsaux. \n" +
                "En revanche, si l'on se \n" +
                "penche en arrière et qu'on tire bien \n" +
                "les coudes derrière soi, \n" +
                "on travaille plus le milieu du dos et \n" +
                "le haut des dorsaux.");


        values.put(COLUMN_EXECUTION, "Debout, prendre la barre de traction \n" +
                "avec une prise plus grande que la largeur d'épaules.\n" +
                "Descendre la barre et s’asseoir \n" +
                "sur le siège de la machine en mettant \n" +
                "les genoux sous les boudins. \n" +
                "Pencher le buste un peu en arrière \n" +
                "en cambrant le bas du dos et amener \n" +
                "la barre au dessus de la poitrine en \n" +
                "contractant fortement les dorsaux. \n" +
                "Revenir lentement à la position de départ.");


        values.put(COLUMN_CATEGORIE, "Dos");
        values.put(COLUMN_IMAGE,"intro_tractions");
        values.put(COLUMN_LIEN,"9fEGXlAOC8g");
        db.insert(TABLE_NAME, null, values);

        //tirage nuque
        values.put(COLUMN_TITLE, "Tirage nuque");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation \n" +
                        "sollicite les muscles du dos \n" +
                        "surtout au niveau de la largeur. \n" +
                        "Le travail à la poulie haute permet \n" +
                        "à ceux qui ne sont pas encore capable \n" +
                        "de faire des tractions à la barre \n" +
                        "fixe avec leur poids du corps, de \n" +
                        "muscler leur dos en largeur.\n");


        values.put(COLUMN_MUSCLE, "Principalement le grand dorsal, \n" +
                "grand rond, petit rond et secondairement \n" +
                "les muscles des bras (biceps brachial, brachial antérieur, \n" +
                "long supinateur), les trapèzes (partie inférieure), \n" +
                "rhomboïdes et deltoïdes postérieurs. \n" +
                "Quand la barre est amenée derrière la \n" +
                "nuque avec les bras le long du corps, \n" +
                "on cible les parties externes et inférieures \n" +
                "des grands dorsaux.");


        values.put(COLUMN_EXECUTION, "S’asseoir sur le siège de la machine en \n" +
                "mettant les genoux sous les boudins. \n" +
                "Tenir la barre en prise large, les mains en \n" +
                "pronation. Tirez la barre vers le bas jusqu'à la nuque, \n" +
                "puis revenir lentement à la position de départ. \n" +
                "Le dos reste toujours droit pendant l’exécution.");


        values.put(COLUMN_CATEGORIE, "Dos");
        values.put(COLUMN_IMAGE,"_40_rowing_barre_t");
        values.put(COLUMN_LIEN,"uq8OXMuU4_o");
        db.insert(TABLE_NAME, null, values);


        //epaules
        //Développé devant
        values.put(COLUMN_TITLE, "Développé devant");
        values.put(COLUMN_DESCRIPTION,
                "Les développés sont parfaits \n" +
                        "pour se forger des épaules massives.\n" +
                        " Le développé devant renforce les muscles\n" +
                        " des épaules, surtout l'avant et le côté, \n" +
                        "et indirectement le haut des \n" +
                        "pectoraux et les triceps.");


        values.put(COLUMN_MUSCLE, "Il sollicite les faisceaux\n" +
                " antérieur et moyen du deltoïde, \n" +
                "mais aussi les trapèzes supérieurs, \n" +
                "le haut des pectoraux et les triceps \n" +
                "qui participent dans l’exécution de l’exercice.");


        values.put(COLUMN_EXECUTION, "Position de départ assis \n" +
                "sur un banc légèrement incliné. \n" +
                "Sortir la barre droite des supports, \n" +
                "seul ou avec l’aide d’un partenaire, \n" +
                "les mains en pronation bien écartées \n" +
                "d’une distance supérieur à la largeur \n" +
                "des épaules.\n" +
                "Amener la barre au dessus \n" +
                "de la poitrine en freinant \n" +
                "la descente puis remonter \n" +
                "sans verrouiller les articulations \n" +
                "au sommet. Le dos et les lombaires \n" +
                "restent collés au banc pendant l'exercice, \n" +
                "il ne faut pas trop cambrer.");


        values.put(COLUMN_CATEGORIE, "Epaules");
        values.put(COLUMN_IMAGE,"intro_developpe_devant");

        values.put(COLUMN_LIEN,"enT4QOuE_4I");
        db.insert(TABLE_NAME, null, values);

        //Développé haltère
        values.put(COLUMN_TITLE, "Développé haltère\n");
        values.put(COLUMN_DESCRIPTION,
                "Le développé réalisé aux \n" +
                        "haltères est un exercice de\n" +
                        " musculation de base qui vous permettra \n" +
                        "de bien développer vos épaules. \n" +
                        "Un mouvement efficace et recommandé aux débutants…");


        values.put(COLUMN_MUSCLE, "Il sollicite les faisceaux \n" +
                "antérieurs (avant de l'épaule) \n" +
                "et moyens (côté de l'épaule) \n" +
                "du deltoïde mais aussi le haut\n" +
                " des pectoraux (faisceau claviculaire), \n" +
                "les trapèzes supérieurs, \n" +
                "les muscles dentelés et les triceps.\n" +
                "Selon la prise, \n" +
                "vous pouvez favoriser \n" +
                "tel ou tel faisceau du \n" +
                "muscle deltoïde. Si vos \n" +
                "paumes sont à l'avant (mains en pronation),\n" +
                " vous sollicitez surtout le côté et l'avant \n" +
                "de l'épaule. Si vos paumes sont face à face \n" +
                "(semi-pronation), vous favorisez encore plus \n" +
                "le travail de l'avant de l'épaule. \n" +
                "Enfin si les paumes sont face au visage \n" +
                "(mains en supination), \n" +
                "c'est l'avant de l'épaule qui \n" +
                "fera la plus grande partie du travail.\n");


        values.put(COLUMN_EXECUTION, "Debout, les deux haltères à la main,\n" +
                " s’asseoir en posant les haltères sur les cuisses. \n" +
                "Cela permet d’éviter \n" +
                "le les ramasser à terre avec tous \n" +
                "les risques que cela comporte. \n" +
                "Position de départ assis sur le banc, \n" +
                "les pieds bien écartés, \n" +
                "les haltères au niveau des épaules avec \n" +
                "les mains en pronation. Développer \n" +
                "les haltères puis revenir lentement à la \n" +
                "position de départ, en freinant la descente.");


        values.put(COLUMN_CATEGORIE, "Epaules");
        values.put(COLUMN_IMAGE,"intro_force_performance");

        values.put(COLUMN_LIEN, "L0z13X2C0UU");
                db.insert(TABLE_NAME, null, values);

        //Développé nuque
        values.put(COLUMN_TITLE, "Développé nuque");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation sollicite \n" +
                        "les épaules. Les développés sont des mouvements \n" +
                        "basiques, très efficaces pour se forger \n" +
                        "de bonnes épaules. Dans cette variante de développé, \n" +
                        "on descend la barre verticalement derrière la nuque, \n" +
                        "jusqu'au niveau des oreilles. L'objectif est de cibler \n" +
                        "le côté et l'arrière de l'épaule, par rapport au développé \n" +
                        "par devant qui lui cible plutôt l'avant.\n");


        values.put(COLUMN_MUSCLE, "Il sollicite les faisceaux moyen \n" +
                "(latéral) et postérieur du deltoïde. \n" +
                "Les trapèzes supérieurs, les triceps et \n" +
                "les dentelés antérieurs interviennent \n" +
                "aussi dans le mouvement.");


        values.put(COLUMN_EXECUTION, "Position de départ, \n" +
                "assis sur un banc droit. \n" +
                "Sortir la barre droite des supports, \n" +
                "seul ou avec l’aide d’un partenaire. \n" +
                "Les mains sont en pronation, bien écartées, \n" +
                "d’une distance supérieure \n" +
                "à la largeur des épaules." +
                "Amener la barre derrière la tête, \n" +
                "jusqu'au niveau des oreilles, \n" +
                "en freinant la descente. \n" +
                "Remonter la barre sans verrouiller \n" +
                "les articulations au sommet. \n" +
                "Le dos et les lombaires restent \n" +
                "collés au banc pendant l'exercice \n" +
                "pour éviter de trop cambrer. \n" +
                "Si vous ressentez trop d'inconfort \n" +
                "au niveau des épaules, que vous manquez de souplesse,\n" +
                "préférez la version barre devant.");


        values.put(COLUMN_CATEGORIE, "Epaules");
        values.put(COLUMN_IMAGE,"intro_developpe_nuque");

        values.put(COLUMN_LIEN, "HYT-voEyjrE");
        db.insert(TABLE_NAME, null, values);

        //triceps
        //Barre front
        values.put(COLUMN_TITLE, "Barre front");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation développe\n" +
                        " la masse et la force des triceps, \n" +
                        "à l'arrière des bras. \n" +
                        "Dans le jargon de la musculation, \n" +
                        "on le surnomme « barre-front » \n" +
                        "du fait qu'on amène la barre au \n" +
                        "niveau du front durant la phase \n" +
                        "négative du mouvement.\n");


        values.put(COLUMN_MUSCLE, "Cet exercice de musculation sollicite \n" +
                "l'ensemble des muscles du triceps. \n" +
                "Le triceps se situe sur la face \n" +
                "postérieure du bras et permet \n" +
                "l'extension de l'avant-bras sur le bras. \n" +
                "Il est composé de 3 faisceaux qui interviennent \n" +
                "plus ou moins suivant la position du bras, \n" +
                "la prise et la résistance plus ou moins forte \n" +
                "appliquée sur le muscle.");


        values.put(COLUMN_EXECUTION, "Position de départ allongé \n" +
                "sur le sol ou sur un banc, \n" +
                "les bras tendus. Les jambes peuvent\n" +
                "être bloquées ou fléchies \n" +
                "sur la poitrine pour éviter de \n" +
                "cambrer trop le bas du dos. \n" +
                "Prendre une barre droite ou coudée, \n" +
                "prise de la largeur des épaules, \n" +
                "les mains en pronation.\n" +
                "\n" +
                "Amener la barre au-dessus \n" +
                "de la tête en tendant les bras. \n" +
                "Ensuite, fléchir les bras jusqu'à \n" +
                "effleurer le front avec la barre, en veillant à \n" +
                "garder les coudes immobiles et les bras serrés \n" +
                "(les bras ne doivent pas s'écarter l'un de l'autre). \n" +
                "Pousser presque jusqu'à l'extension complète.\n" +
                "\n" +
                "Le mouvement décrit un quart d'arc \n" +
                "de cercle et seuls les avants bras \n" +
                "bougent durant l'exercice.");


        values.put(COLUMN_CATEGORIE, "Triceps");
        values.put(COLUMN_IMAGE,"intro_barre_front");

        values.put(COLUMN_LIEN, "-a4FR3zmdJ8");
        db.insert(TABLE_NAME, null, values);

        //Extensions au-dessus de la tête
        values.put(COLUMN_TITLE, "Extensions au-dessus de la tête");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation développe \n" +
                        "les muscles triceps qui se situe sur \n" +
                        "la face postérieure du bras. \n" +
                        "La position du bras au-dessus de la tête rend l'exercice \n" +
                        "plus difficile par rapport au barre-front ou \n" +
                        "au travail à la poulie haute. \n" +
                        "La charge utilisée sera donc moins importante.");


        values.put(COLUMN_MUSCLE, "Cet exercice de musculation \n" +
                "sollicite l'ensemble des muscles du triceps. \n" +
                "Le triceps permet l'extension de l’avant-bras sur le bras. \n" +
                "Il est composé de trois faisceaux qui interviennent \n" +
                "plus ou moins suivant la position du bras, \n" +
                "la prise et la résistance plus ou moins forte \n" +
                "appliquée sur les muscles.");


        values.put(COLUMN_EXECUTION, "Position de départ assis sur un banc,\n" +
                " le dos bien droit, un haltère dans \n" +
                "la main en position « marteau » et le bras \n" +
                "tendu au-dessus de la tête. \n" +
                "Descendre l'haltère derrière la tête \n" +
                "en gardant le coude pointé vers le plafond \n" +
                "(bras vertical), le plus bas possible et \n" +
                "sans heurter le cou votre cou. \n" +
                "Remonter la charge et arrêter l'extension \n" +
                "avant de tendre complètement le bras. \n" +
                "Seul l'avant-bras doit bouger pendant \n" +
                "l'exécution du mouvement, de sorte à se\n" +
                " concentrer sur le triceps.");


        values.put(COLUMN_CATEGORIE, "Triceps");
        values.put(COLUMN_IMAGE,"intro_extensions");

        values.put(COLUMN_LIEN, "kJ32PRK3LRo");
        db.insert(TABLE_NAME, null, values);

        //kick back
        values.put(COLUMN_TITLE, "kick back");
        values.put(COLUMN_DESCRIPTION,
                "Cet exercice de musculation appelé \n" +
                        "« kick back » développe les triceps à \n" +
                        "l'arrière du bras. C'est un exercice de \n" +
                        "finition qu'on place généralement à la \n" +
                        "fin de la séance de triceps pour obtenir \n" +
                        "une bonne congestion dans ces muscles.");


        values.put(COLUMN_MUSCLE, "Il sollicite les muscles du \n" +
                "triceps plus particulièrement les \n" +
                "faisceaux internes et externes. \n" +
                "L'arrière d'épaule est également mise \n" +
                "à contribution pour maintenir le bras immobile.");


        values.put(COLUMN_EXECUTION, "Position de départ buste penché en avant, \n" +
                "un genou et la main libre en appuis sur le banc. \n" +
                "Le bras qui tient l'haltère prés du \n" +
                "flanc est parallèle au sol, et le coude \n" +
                "est plus haut que le torse. Tendre le bras \n" +
                "en amenant votre main en pronation, \n" +
                "paume vers le plafond. \n" +
                "Le corps reste en statique et seul l'avant-bras bouge \n" +
                "durant l'exercice.\n");


        values.put(COLUMN_CATEGORIE, "Triceps");
        values.put(COLUMN_IMAGE,"intro_kick_back");

        values.put(COLUMN_LIEN, "uZZ2630ELp8");
        db.insert(TABLE_NAME, null, values);

        // Quadriceps
        //leg extension
        values.put(COLUMN_TITLE, "Leg extension");
        values.put(COLUMN_DESCRIPTION,
                "\n" +
                        "Cet exercice de musculation \n" +
                        "sollicite les muscles du bas de la cuisse. \n" +
                        "C'est un exercice d’isolation pour le quadriceps \n" +
                        "qui termine en général la séance de cuisses. \n" +
                        "Il ne vaut pas le squat mais permet de travailler \n" +
                        "les cuisses sans solliciter les fessiers et dos. \n" +
                        "En revanche, il impose un grand stress au niveau \n" +
                        "du genou et ne peut donc pas convenir aux personnes \n" +
                        "ayant des problèmes au niveau de cette articulation.");


        values.put(COLUMN_MUSCLE, "Le quadriceps formé par le droit antérieur, \n" +
                "le biceps crural, le vaste externe et interne.");


        values.put(COLUMN_EXECUTION, "Commencez par régler la charge à \n" +
                "l’arrière de la machine, le dossier et \n" +
                "la hauteur des manchons qui ne doivent \n" +
                "pas reposer sur le tibia ou sur la pointe \n" +
                "des pieds mais entre les deux. \n" +
                "Assis sur la machine à quadriceps, \n" +
                "dos et lombaires collés au siège, buste droit, \n" +
                "les pieds calés sous les manchons et les mains \n" +
                "qui tiennent les poignets sur le côté. \n" +
                "Remonter la charge puis maintenir \n" +
                "la contraction en position haute. \n" +
                "Revenir à la position de départ en freinant la descente.\n");


        values.put(COLUMN_CATEGORIE, "Quadriceps");
        values.put(COLUMN_IMAGE,"quadriceps");

        values.put(COLUMN_LIEN, "0qm5IhVqIDw");
        db.insert(TABLE_NAME, null, values);

        //Sissy squat
        values.put(COLUMN_TITLE, "Sissy squat");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation sollicite les quadriceps,\n" +
                "les muscles situés sur le bas de la cuisse. \n" +
                "C'est un exercice de finition que vous pouvez utiliser \n" +
                "en fin de séance. \n" +
                "Vous pouvez l’exécuter sur une machine prévue pour ou sans, \n" +
                "comme sur la démonstration. Il peut remplacer \n" +
                "l'exercice de leg-extension. Il permet d'accentuer \n" +
                "le travail du droit antérieur par un étirement important.");

        values.put(COLUMN_MUSCLE, "Le quadriceps formé par le droit antérieur, \n" +
                "le biceps crural, le vaste externe et interne.");


        values.put(COLUMN_EXECUTION, "Si vous utilisez un appareil spécifique,\n" +
                " commencez par régler la position des manchons \n" +
                "pour que vos pieds soient bien calés et immobiles. \n" +
                "Se laisser tomber en arrière sans craindre la chute, \n" +
                "en faisant intervenir d’abord les quadriceps et \n" +
                "ensuite les fessiers. Quand les cuisses sont parallèles \n" +
                "au sol, remonter jusqu’à la position de départ en \n" +
                "essayant de faire porter le travail sur les quadriceps.\n" +
                " Garder vos muscles constamment sous tension, \n" +
                "sans prendre de repos en position haute.\n" +
                "Si vous travaillez avec le seul poids du corps, \n" +
                "fléchir les genoux en les amenant vers l'avant tout \n" +
                "en gardant le dos droit et les abdominaux gainés. \n" +
                "Revenir à la position initiale en contractant les \n" +
                "quadriceps et en poussant sur la pointe des pieds.");


        values.put(COLUMN_CATEGORIE, "Quadriceps");
        values.put(COLUMN_IMAGE,"intro_sissy");

        values.put(COLUMN_LIEN, "-UVdsZDSoCM");
        db.insert(TABLE_NAME, null, values);

        //Cuisses
        //Le squat
        values.put(COLUMN_TITLE, "Le squat");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation sollicite et\n" +
                "développe l’ensemble du \n" +
                "corps mais vise principalement \n" +
                "les muscles des cuisses et les fessiers. \n" +
                "C'est l'exercice roi de la musculation. \n" +
                "« Ceux qui ne font pas de squat, ne \n" +
                "font pas de musculation » est une \n" +
                "expression qu'on entend souvent de \n" +
                "la bouche des culturistes.");

        values.put(COLUMN_MUSCLE, "Le grand fessier, \n" +
                "quadriceps \n" +
                "(droit antérieur, biceps crural, vaste externe et interne), \n" +
                "arrière cuisses (demi membraneux, demi-tendineux), \n" +
                "soléaire des mollets, les muscles fixateurs abdominaux, \n" +
                "petit et moyen fessiers, adducteurs, dos (spinaux, lombaires) \n" +
                "et dans une moindre mesure les muscles de la \n" +
                "ceinture scapulaire (épaules, trapèzes) pour maintenir la barre.\n");


        values.put(COLUMN_EXECUTION, "Debout, la barre reposant sur les trapèzes, \n" +
                "mains espacées de la largeur des \n" +
                "épaules ou plus, pieds en canard - à dix heure dix - \n" +
                "légèrement plus écartés que la largeur des épaules et \n" +
                "placés dans l’axe des genoux. Fléchir les genoux et \n" +
                "pousser les fesses en arrière avec le buste droit. \n" +
                "Descendre théoriquement jusqu'à ce que vos cuisses \n" +
                "soient parallèle au sol. Limitez l’amplitude si \n" +
                "vous sentez que vous allez arrondir le dos ou si \n" +
                "vos talons décollent du sol. La barre descend et \n" +
                "remonte verticalement, sans arrêts en position \n" +
                "haute et rebonds en position basse. \n" +
                "L'équilibre peut être difficile au début, \n" +
                "il faut du temps pour maîtriser l'exercice.\n");


        values.put(COLUMN_CATEGORIE, "Cuisses");
        values.put(COLUMN_IMAGE,"intro_programme_cuisses");

        values.put(COLUMN_LIEN,"fypQ8tQ1OP0");
        db.insert(TABLE_NAME, null, values);
        //Presse à cuisses
        values.put(COLUMN_TITLE, "Presse à cuisses");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation \n" +
                "sollicite les muscles des cuisses et \n" +
                "les fessiers. Il est considéré comme moins \n" +
                "risqué que le squat à la barre car la charge \n" +
                "est répartie sur l’ensemble du dos grâce au \n" +
                "dossier de la machine. Il n’est cependant pas \n" +
                "impossible de se blesser dessus si on pousse \n" +
                "n'importe comment. De plus, les presses à \n" +
                "cuisses sont souvent mal conçues et ne \n" +
                "correspondent pas à toutes les morphologies \n" +
                "ce qui peut poser des problèmes.");

        values.put(COLUMN_MUSCLE, "Quadriceps (droit antérieur, biceps crural, \n" +
                "vaste externe et interne), fessiers, \n" +
                "arrière cuisses (demi membraneux, demi-tendineux), \n" +
                "soléaire des mollets, lombaires, abdominaux.");


        values.put(COLUMN_EXECUTION, "Se placer sur la presse à cuisses, \n" +
                "pieds sur la plateforme écartés de la \n" +
                "largeur des épaules, pointes de pieds vers \n" +
                "l'extérieur, dos et lombaires bien calés \n" +
                "contre le dossier. Tenir les deux poignées \n" +
                "qui se trouvent de chaque côté. \n" +
                "Décoller la charge jusqu'à ce que vos \n" +
                "jambes soient presque tendues mais sans \n" +
                "les tendre complètement. Ne pas bloquer les \n" +
                "articulations en fin de mouvement. \n" +
                "Revenir à la position de départ mais arrêter \n" +
                "la descente avant de décoller le bassin ; \n" +
                "il ne faut pas arrondir le bas du dos ");


        values.put(COLUMN_CATEGORIE, "Cuisses");
        values.put(COLUMN_IMAGE,"intro_presse_cuisses");

        values.put(COLUMN_LIEN,"Ef1wVCbLWN0");
        db.insert(TABLE_NAME, null, values);

        //Squat une jambe
        values.put(COLUMN_TITLE, "Squat une jambe");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation sollicite \n" +
                "les muscles des cuisses et les fessiers.\n" +
                " Il est très efficace et excellent pour développer \n" +
                "la coordination et l’équilibre, en plus de faire \n" +
                "gagner du muscle aux cuisses et fessiers. \n" +
                "Il s’exécute de la même façon que le squat \n" +
                "mais sur une seule jambe.");

        values.put(COLUMN_MUSCLE, "Quadriceps \n" +
                "(droit antérieur, biceps crural, vaste externe et interne), \n" +
                "fessiers, ischios, grand adducteur, petit adducteur et soléaire.\n");


        values.put(COLUMN_EXECUTION, "Debout, une main sur un support - \n" +
                "pour un meilleur équilibre -, \n" +
                "l’autre libre si vous travaillez \n" +
                "avec le poids du corps ou avec un haltère. \n" +
                "Fléchir le genou et amener la cuisse parallèle \n" +
                "au sol en gardant le dos droit. \n" +
                "Remonter à la position de départ en \n" +
                "contractant bien les quadriceps et fessiers. \n" +
                "La jambe arrière reste pliée et ne touche pas le sol. \n" +
                "Pour cela, utilisez un step." +
                "la descente avant de décoller le bassin ; \n" +
                "il ne faut pas arrondir le bas du dos ");


        values.put(COLUMN_CATEGORIE, "Cuisses");
        values.put(COLUMN_IMAGE,"intro_squat_1_jambe");

        values.put(COLUMN_LIEN,
                "xjvr3aySTuk");
        db.insert(TABLE_NAME, null, values);

        //ischios

        //Leg curl assis
        values.put(COLUMN_TITLE, "Leg curl assis");
        values.put(COLUMN_DESCRIPTION,"Le leg curl assis est sans doute \n" +
                "le mouvement qui est le plus efficace \n" +
                "lorsqu'il s'agit de travailler les \n" +
                "ischio-jambiers. De plus, \n" +
                "il est très simple puisqu'il \n" +
                "suffit de s’asseoir sur la chaise à leg curl,\n" +
                "de caler les jambes entre les deux supports et \n" +
                "d'essayer de fléchir les genoux à 90 degrés. \n" +
                "Puis, il faut contrôler le poids lorsque les \n" +
                "jambes remontent. Les poignées, \n" +
                "situées sur le côté de l'appareil, \n" +
                "permettent de conserver une bonne stabilité.");

        values.put(COLUMN_MUSCLE, "Le leg curl assis cible principalement \n" +
                "les muscles ischio-jambiers, situés à \n" +
                "l'arrière de la cuisse, ainsi que les mollets.\n" +
                "De façon secondaire, il sollicite aussi les adducteurs\n");


        values.put(COLUMN_EXECUTION, "Asseyez-vous dans la machine à \n" +
                "leg curl assis, et passez vos cuisses \n" +
                "sous le « boudin rembourré ». \n" +
                "Placez ensuite vos chevilles devant le \n" +
                "deuxième « boudin ». Il est conseillé de \n" +
                "remonter vos chaussettes afin d'améliorer \n" +
                "votre confort et de permettre au support de bien \n" +
                "rouler. Vous pouvez également arrondir un peu \n" +
                "le bas de votre dos afin d'éviter les tricheries et \n" +
                "les mouvements brusques.");


        values.put(COLUMN_CATEGORIE, "Ischios");
        values.put(COLUMN_IMAGE,"intro_leg_curl_assis");

        values.put(COLUMN_LIEN,
                "NDh0tBUhsWE");
        db.insert(TABLE_NAME, null, values);
        // Leg curl couché
        values.put(COLUMN_TITLE, "Leg curl couché");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation \n" +
                "sollicite et tonifie l’arrière des cuisses. \n" +
                "C’est un exercice d’isolation qui est efficace \n" +
                "pour ces muscles mais moins rentable \n" +
                "que les exercices poly articulaires comme \n" +
                "le soulevé de terre et ses variantes qui vous \n" +
                "feront prendre plus de masse aux cuisses.");

        values.put(COLUMN_MUSCLE, "Les ischios jambiers \n" +
                "(le biceps fémoral, le demi-tendineux et le demi-membraneux), \n" +
                "les mollets (jumeaux).");


        values.put(COLUMN_EXECUTION, "Couché à plat ventre sur la machine, la tête face au sol, \n" +
                "les mains sur les poignets. \n" +
                "Ramener les coussins le plus possible \n" +
                "vers les fessiers en contrastant fort \n" +
                "l’arrière des cuisses. \n" +
                "Redescendre la charge lentement en gardant \n" +
                "la tension dans les muscles.\n" +
                "Les manchons doivent être placés sous les \n" +
                "tendons d'Achille et non pas derrière les \n" +
                "mollets ou les talons. \n" +
                "Les genoux doivent être dans l’axe de la machine. \n" +
                "Les fessiers et abdominaux peuvent être gainés pendant \n" +
                "l'exécution de l’exercice pour éviter de trop cambrer.\n" +
                "A la maison, si vous utilisez un banc de musculation \n" +
                "plat qui offre cette option, ajoutez un coussin sous \n" +
                "les hanches pour éviter d’avoir une position trop cambrée.\n");


        values.put(COLUMN_CATEGORIE, "Ischios");
        values.put(COLUMN_IMAGE,"intro_leg_curl");

        values.put(COLUMN_LIEN,
                "ZAvezR4Fi6w");
        db.insert(TABLE_NAME, null, values);

        //Soulevé de terre
        values.put(COLUMN_TITLE, "Soulevé de terre");
        values.put(COLUMN_DESCRIPTION,"Cet exercice de musculation de \n" +
                "base développe tout le corps et \n" +
                "permet de gagner beaucoup de force. \n" +
                "Rien ne vaut le soulevé de terre ou \n" +
                "« Deadlift » pour prendre de la masse, \n" +
                "à part peut être le squat ! \n" +
                "Il travaille les quadriceps, les arrière cuisses, \n" +
                "les fessiers, le dos, les mollets, les abdos, \n" +
                "les lombaires, les trapèzes et même les avant-bras.\n");

        values.put(COLUMN_MUSCLE, "Le grand fessier, \n" +
                "quadriceps (droit antérieur, biceps crural, vaste externe et interne), \n" +
                "arrières cuisses (demi membraneux, demi-tendineux), \n" +
                "soléaire des mollets, les muscles fixateurs abdominaux, \n" +
                "adducteurs, dos (spinaux, lombaires, dorsaux), \n" +
                "avant-bras et dans une moindre mesure les \n" +
                "muscles de la ceinture scapulaire (épaules, trapèzes) \n" +
                "pour tenir la barre.");


        values.put(COLUMN_EXECUTION, "Il faut d'abord régler la hauteur de la barre, \n" +
                "à mi-mollet ou plus haut. Prendre la \n" +
                "barre avec un écartement des mains \n" +
                "de la largeur des épaules. \n" +
                "Les pieds sont suffisamment écartés et \n" +
                "pointés vers l'extérieur. \n" +
                "Se positionner, garder la cambrure naturelle \n" +
                "du bas du dos et contracter les abdominaux. \n" +
                "Lever la barre jusqu'au niveau des genoux à \n" +
                "l’aide des quadriceps puis redresser le buste.\n");


        values.put(COLUMN_CATEGORIE, "Ischios");
        values.put(COLUMN_IMAGE,"ischios");

        values.put(COLUMN_LIEN,
                "ws-KbEQRr-o");
        db.insert(TABLE_NAME, null, values);





    }

}
