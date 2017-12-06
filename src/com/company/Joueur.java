package com.company;
public class Joueur {

        private static int nbreJoueur=0;
        private int id = 0;
        private String nom;
        private String prenom;
        int categorie;
        public boolean utilise = false;

    public Joueur(){
        this("nom","prenom",1);
    }

    public Joueur(String nom, String prenom, int categorie){
            this.nbreJoueur++;
            this.id = nbreJoueur;
            this.id++;
            this.nom = nom;
            this.prenom = prenom;
            this.categorie = categorie;

        }

        public void setUtilise(boolean utilise){
            this.utilise = utilise;
        }
    public boolean estUtilise(){
        return utilise;
    }

    public int getIdJoueur(){

        return this.id;
    }

    public String getNom() {

        return this.nom;
    }

    public String getPrenom() {

        return this.prenom;
    }

    public int getCategorie(){

        return this.categorie;
    }
}

