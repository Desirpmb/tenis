package TestsIntegration;

import com.company.Joueur;
import com.company.Match;
import junit.framework.TestCase;

public class TestincrementerPointInte extends TestCase {

//Test de la bonne incrémentation des sous points
    public void test1()throws Exception{

        Match match = new Match(new Joueur(), new Joueur(),null);
        //Vérifcation de la boucle d'incrémentation des sous points
        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));
    }
//Test de la bonne incrémentation des sous points et des points
    public void test2()throws Exception{
        Match match = new Match(new Joueur(), new Joueur(),null);
        //Vérification de la boucle d'incrémentation des points
        assertEquals(2, match.incrementerPointSept(1));
        assertEquals(3, match.incrementerPointSept(2));
        assertEquals(4, match.incrementerPointSept(3));
        assertEquals(5, match.incrementerPointSept(4));
        assertEquals(0, match.incrementerPointSept(5));

        //Vérifcation de la boucle d'incrémentation des sous points
        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));

    }

    //Test de la bonne incrémentation des sous point, des point et l'incrémentation des point du joueur1 du match
    public void testincrementerPointJoueur1(){


        Match match = new Match(new Joueur(), new Joueur(),null);
        //Vérification de la boucle d'incrémentation des points
        assertEquals(2, match.incrementerPointSept(1));
        assertEquals(3, match.incrementerPointSept(2));
        assertEquals(4, match.incrementerPointSept(3));
        assertEquals(5, match.incrementerPointSept(4));
        assertEquals(0, match.incrementerPointSept(5));

        //Vérifcation de la boucle d'incrémentation des sous points
        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));


        int pointjooueur1 = match.getPointjoueur1();

        //Verification de la bonne incrémentation des points du joueur1 du match
        match.setPointjoueur1(0);
        match.incrementerPoint1Joueur1();
        assertEquals(15,match.getPointjoueur1());
        match.setPointjoueur1(40);
        match.incrementerPoint1Joueur1();
        assertEquals(0,match.getPointjoueur1());


    }


}