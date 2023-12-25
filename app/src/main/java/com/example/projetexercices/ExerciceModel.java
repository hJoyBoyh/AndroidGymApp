package com.example.projetexercices;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ExerciceModel implements Parcelable {

    public static String EXTRA ="EXTRA";

    private int id;
    private String title;
    private String description;
    private String muscle;
    private String execution;
    private String image;

    private String lien;
    private String categorie;

    public ExerciceModel(int id, String title, String description, String muscle, String execution ,String image,String lien,String categorie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.muscle = muscle;
        this.execution = execution;
        this.image = image;
        this.lien = lien;
        this.categorie = categorie;
    }

    public ExerciceModel(Parcel in) {

            id = in.readInt();
            title= in.readString();
            description = in.readString();
            muscle = in.readString();
            execution = in.readString();
            image= in.readString();
            lien= in.readString();
            categorie = in.readString();



    }

    @Override
    public String toString() {
        return "ExerciceModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", muscle='" + muscle + '\'' +
                ", execution='" + execution + '\'' +
                ", image='" + image + '\'' +
                ", lien='" + lien + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public static final Creator<ExerciceModel> CREATOR = new Creator<ExerciceModel>() {
        @Override
        public ExerciceModel createFromParcel(Parcel in) {
            return new ExerciceModel(in);
        }

        @Override
        public ExerciceModel[] newArray(int size) {
            return new ExerciceModel[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(muscle);
        parcel.writeString(execution);
        parcel.writeString(image);
        parcel.writeString(lien);
        parcel.writeString(categorie);



    }
}

