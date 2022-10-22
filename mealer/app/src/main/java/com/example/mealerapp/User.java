package com.example.mealerapp;
public  abstract  class User{
    String Prenom;
    String Nom;
    String Courriel;
    String MotDePasse;
    String Adresse;

    //Constructeur
    public User (String prenom,String nom, String courriel, String motDePasse, String adresse){
        this.Prenom = prenom;
        this.Nom = nom;
        this.Courriel = courriel;
        this.MotDePasse = motDePasse;
        this.Adresse = adresse;
    }


    //S'inscrire
    public void SignUp(User user){
        //Récupérer les entrées de l'utilisateur dans des variables
        //Ajouter un ligne a la table User avec les contenus des variables récupérés

    }

    //Se connecter
    public void SignIn(User user){
        //Recupérer email entré
        //Récupérer mot de passe entré
        //Regarder dans la table à la ligne où se trouve l'email si le MotDePasse est identique

    }

    //Se déconnecter
    public void SignOut(User user){

    }
}
