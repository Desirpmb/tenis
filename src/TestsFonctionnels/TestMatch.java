package TestsFonctionnels;

import com.company.Joueur;
import com.company.ListeJoueur;
import com.company.Match;
import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;

public class TestMatch extends TestCase {
//Test du déroulement complet d'un match: le joueur1 doit être le gagnant.

    public void testincrePointSept()throws Exception{
        ListeJoueur listeJoueur = new ListeJoueur();

        Match match = new Match( listeJoueur.getJoueurDisponible(1),  listeJoueur.getJoueurDisponible(2), listeJoueur);
        for (int i =1; i<4; i++) {
            //Verification qu'il y a bien aucun gagnant
            assertEquals("Aucun", match.getNomGagnant());
            // incrémentation des sous points du joueur1 2x
            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();
            //Vérification que le nombre de point gagné par le joueur 1 et 2 est bien de 0
            assertEquals(0, match.getPointseptjoueur1());
            assertEquals(0, match.getPointseptjoueur2());
            //Vérification que nombre de sous points  du joueur1 est bien de 30
            assertEquals(30, match.getPointjoueur1());
            //Vérification que le nombre de sous point du joueur 2 est bien resté à 0
            assertEquals(0, match.getPointjoueur2());


            match.incrementerPoint1Joueur1();
            match.incrementerPoint1Joueur1();

            //Verification que le joueur 1 remporte bien 1 point  apres avoir gagné 4 sous point
            assertEquals(1, match.getPointseptjoueur1());

            //Vérification que le nombre des sous  points des deux joueurs sont bien remis à 0
            assertEquals(0, match.getPointjoueur1());
            assertEquals(0, match.getPointjoueur2());


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

            //on verifie que le nombre de sept s'incrémente à la fin de chaque sept
            assertEquals(i, match.getNombreseptjoueur1());
        }
        // Fin du match on verifie que le nom du joueur 1, le joueur gagnant est bien dans la viariable nomgagnant
        assertEquals(match.getJoueur1().getNom(), match.getNomGagnant());

    }
}
