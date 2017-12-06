package TestsFonctionnels;

import com.company.Joueur;
import com.company.ListeJoueur;
import com.company.Match;
import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;

public class TestMatch extends TestCase {


    public void testincrePointSept()throws Exception{
        ListeJoueur listeJoueur = new ListeJoueur();

        Match match = new Match( listeJoueur.getJoueurDisponible(1),  listeJoueur.getJoueurDisponible(2), listeJoueur);
        for (int i =1; i<4; i++) {
            assertEquals("Aucun", match.getNomGagnant());
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointseptjoueur1());
            assertEquals(30, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(1, match.getPointseptjoueur1());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(30, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(2, match.getPointseptjoueur1());


            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(3, match.getPointseptjoueur1());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(4, match.getPointseptjoueur1());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(5, match.getPointseptjoueur1());

            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());
            assertEquals(0, match.getPointseptjoueur1());
            assertEquals(i, match.getNombreseptjoueur1());
        }
        assertEquals(match.getJoueur1().getNom(), match.getNomGagnant());

    }
}
