package com.example.mealerapp;

public class Cooker extends User{

    //Constructeur
    //On a en paramettre les paramètres nécessaires à la création de l'objet
    public Cooker(String prenom,String nom, String courriel, String motDePasse, String adresse, String typeUser){
        //On appelle le constructeur de la classe parent User
        super(prenom, nom, courriel, motDePasse, adresse);
         typeUser= "Cuisinier";
    }

}
