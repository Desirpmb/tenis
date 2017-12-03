package com.company;





public class MatchBuilder {
    private ListeJoueur listejoueur;





    public MatchBuilder(ListeJoueur liste){
        this.listejoueur = liste;

    }



    public Match creationMatch() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int nbrAleatoire = Aleatoire.nombreAleatoireInt(0,listejoueur.getTailleDisponible()-1);
        Joueur joueur1 = listejoueur.getJoueurDisponible(nbrAleatoire);
        nbrAleatoire = Aleatoire.nombreAleatoireInt(0,listejoueur.getTailleDisponible()-1);
        Joueur joueur2 = listejoueur.getJoueurDisponible(nbrAleatoire);

        return new Match(joueur1, joueur2, listejoueur);
    }



}
