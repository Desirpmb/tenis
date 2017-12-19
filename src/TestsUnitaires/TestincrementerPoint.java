package TestsUnitaires;

import com.company.Joueur;
import com.company.Match;
import junit.framework.TestCase;

public class TestincrementerPoint extends TestCase {


    //Test du l'incrémentation des sous points
public void testincrePoint()throws Exception{

    Match match = new Match(null, null,null);
    assertEquals(15, match.incrementerPoint(0));
    assertEquals(30, match.incrementerPoint(15));
    assertEquals(40, match.incrementerPoint(30));
    assertEquals(0, match.incrementerPoint(40));
}
// Test de l'incrémentation des points
    public void testincrePointSept()throws Exception{
        Match match = new Match(null, null,null);
        assertEquals(2, match.incrementerPointSept(1));
        assertEquals(3, match.incrementerPointSept(2));
        assertEquals(4, match.incrementerPointSept(3));
        assertEquals(5, match.incrementerPointSept(4));
        assertEquals(0, match.incrementerPointSept(5));

    }
// Test de l'incrémentation des points du joueur1 du match
    public void testincrementerPointJoueur1(){
        Match match = new Match(new Joueur(), new Joueur(),null);
        int pointjooueur1 = match.getPointjoueur1();

        match.setPointjoueur1(0);
        match.incrementerPoint1Joueur1();
        assertEquals(15,match.getPointjoueur1());
        match.setPointjoueur1(40);
        match.setPointjoueur2(40);
        match.incrementerPoint1Joueur1();
        assertEquals(0,match.getPointjoueur1());
        assertEquals(0,match.getPointjoueur2());


    }


}
