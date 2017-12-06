package TestsIntegration;

import com.company.Joueur;
import com.company.Match;
import junit.framework.TestCase;

public class TestincrementerPointInte extends TestCase {


    public void test1()throws Exception{

        Match match = new Match(new Joueur(), new Joueur(),null);
        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));
    }

    public void test2()throws Exception{
        Match match = new Match(new Joueur(), new Joueur(),null);
        assertEquals(2, match.incrementerPointSept(1));
        assertEquals(0, match.incrementerPointSept(5));

        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));

    }

    public void testincrementerPointJoueur1(){


        Match match = new Match(new Joueur(), new Joueur(),null);
        assertEquals(2, match.incrementerPointSept(1));
        assertEquals(0, match.incrementerPointSept(5));

        assertEquals(15, match.incrementerPoint(0));
        assertEquals(30, match.incrementerPoint(15));
        assertEquals(40, match.incrementerPoint(30));
        assertEquals(0, match.incrementerPoint(40));

        int pointjooueur1 = match.getPointjoueur1();

        match.setPointjoueur1(0);
        match.incrementerPoint1Joueur1();
        assertEquals(15,match.getPointjoueur1());
        match.setPointjoueur1(40);
        match.incrementerPoint1Joueur1();
        assertEquals(0,match.getPointjoueur1());


    }


}