package com.company;

import java.util.HashMap;

public class ListeMatch {

    private HashMap<Integer, Match> liste;

    public ListeMatch(){
        this.liste = new HashMap<Integer, Match>();

    }

    public void addMatch(Match match){
        match.start();
        liste.put(match.getMatchId(), match);

    }

    public void removeMatch(Match match){
        liste.remove(match.getMatchId());
    }

    public HashMap<Integer, Match> getListe() {
        return liste;
    }

    public Match getMatch(int id){

        return liste.get(id);
    }

    public boolean verificationExistant(int idMatch, int idJoueur){
        if(liste.containsKey(idMatch)){
            Match match = liste.get(idMatch);
            if((match.getJoueur1().getIdJoueur()==idJoueur)||(match.getJoueur2().getIdJoueur()==idJoueur)){
                if(!match.isFin()){
                    return true;
                }

            }
        }
        return false;
    }



}
