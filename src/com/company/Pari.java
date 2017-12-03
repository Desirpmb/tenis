package com.company;
public class Pari {

    private static int nbrePari = 0;
    private int id = 0;
    Joueur joueur;
    Match match;
    float montantMise;
    float montantGagner;

    public Pari(float montantMise, Match match, Joueur joueur){

        this.nbrePari++;
        this.id = nbrePari;
        this.montantMise = montantMise;
        this.match = match;
        this.joueur = joueur;


    }



    public float getMontantMise() {
        return montantMise;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public int getId() {
        return id;
    }

    public float getMontantgagner(){
       return match.montantgagne(joueur.getIdJoueur());
    }


}
