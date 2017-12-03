package com.company;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Cette classe permet de génerer des nombres aléatoires
 */
public class Aleatoire {
    // génère un entier aléatoire entre borne min et max
    public static int nombreAleatoireInt(int min, int max) {
        Random rand = new Random();
        int nombrealeatoire = rand.nextInt(max - min + 1) + min;
        return nombrealeatoire;
    }
    // génère un réel positif entre borne avec une précision de 3 chiffres après la virgule
    public static double nombreAleatoireDouble(double min, double max) {

        double nombrealeatoire = min + (double) Math.random() * (max - min);

        // formatage à 3 decimal maximum
        BigDecimal format = new BigDecimal(nombrealeatoire);
        format = format.setScale(3, BigDecimal.ROUND_DOWN);
        nombrealeatoire = format.doubleValue();
        return nombrealeatoire;
    }
}

