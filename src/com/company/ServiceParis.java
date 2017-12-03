package com.company;

import java.util.HashMap;

public class ServiceParis {
    HashMap<Integer, Pari> listparis = new HashMap<Integer, Pari>();

    public ServiceParis(){

    }

    public int addPari(Pari pari){
        listparis.put(pari.getId(), pari);
        return pari.getId();
    }

    public boolean removePariById(int id){
        if(listparis.containsKey(id)){
            listparis.remove(id);
            return true;
        }
        return false;
    }

    public Pari getPariByID(int id){
        return listparis.get(id);
    }

    public float getMontantGain(int idPari){
        float montant;
        Pari pari = listparis.get(idPari);
        montant = pari.getMontantgagner();
        return  montant;
    }


}

