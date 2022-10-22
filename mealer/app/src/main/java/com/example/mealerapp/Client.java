package com.example.mealerapp;


public class Client extends User{

        //Constructeur
        //On a en paramettre les paramètres nécessaires à la création de l'objet
        public Client(String prenom,String nom, String courriel, String motDePasse, String adresse,String typeUser) {
            super(prenom, nom, courriel, motDePasse, adresse);
            typeUser = "Client";
            //On appelle le constructeur de la classe parent User
        }
}


