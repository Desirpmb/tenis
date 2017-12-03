package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeJoueur {
    HashMap<Integer, Joueur> listejoueur = new HashMap<Integer, Joueur>();
    ArrayList<Joueur> listeDisponible = new ArrayList<Joueur>();


    private String nomJoueur[] = {"Nadal", "Djokovic", "Federer", "Tsonga", "Monfils", "Gasquet", "Murray", "Raonic", "Shapovalov", "Sugita", "Paire", "Lorenzi", "Khachanov", "Verdasco"};
    private String prenomJoueur[] = {"Rafael", "Novak", "Roger", "Jo-Wilfried", "Gaël", "Richard", "Andy", "Milos", "Denis", "Yuichi", "Benoît", "Paolo", "Karen", "Fernando"};
    private int categorie[] = {2,2,2,2,1,1,1,1,1,1,1,1,1,1,0,0,0};

    public ListeJoueur(){
        creationlisteJoueur();
    }
    public void creationlisteJoueur(){

        for(int i=1;i<=14;i++){
            Joueur joueur = new Joueur(nomJoueur[i-1],prenomJoueur[i-1], categorie[i]);
            this.listejoueur.put(joueur.getIdJoueur(),joueur);
            this.listeDisponible.add(joueur);
        }
    }

    public int getTailleDisponible(){

       int  size = listeDisponible.size();
      //  System.err.println("[[[[size: "+size+"]]]]");
        return size;
    }

    public Joueur getJoueurDisponible(int index){

        Joueur joueur = listeDisponible.get(index);
        listeDisponible.remove(joueur);

        return joueur;


    }
    public Joueur getJoueurByID(int id){
        return listejoueur.get(id);
    }

    public void setJoueurDisponible(Joueur joueur){
        listeDisponible.add(joueur);
    }



}
