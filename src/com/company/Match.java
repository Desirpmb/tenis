package com.company;

public class Match extends Thread {

    private static final int[][] tableau = {{50,75,85},{75,50,65},{85,65,50}};
    private static int nombredematch= 0;
    private int machtid;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur service;
    private Joueur gagnant;
    private int nbparijoueur1=0;
    private int nbparijoueur2=0;
    private Joueur joueurservice;


    private int pointjoueur1;
    private int pointjoueur2;
    private int nombreseptjoueur1;
    private int nombreseptjoueur2;
    private int pointseptjoueur1;
    private int pointseptjoueur2;
    private int pourcentage;
    private int nbsept = 1;
    private ListeJoueur listejoueur;

    private float misejoueur1 =0;
    private float misejoueur2 =0;
    private int nombreevenement =0;
    private String nomJoueurEvenement;


    private int[][] grille = {{0,0},{0,0},{0,0},{0,0}};

    private boolean fin = false;

   private Chronometre chronometre;

    public Match(Joueur joueur1, Joueur joueur2, ListeJoueur listejoueur) {

        this.listejoueur = listejoueur;
        if(joueur1.getCategorie()>= joueur2.getCategorie()) {
            this.joueur1 = joueur1;
            this.joueur2 = joueur2;
        }else{
            this.joueur1 = joueur2;
            this.joueur2 = joueur1;
        }
        this.machtid = nombredematch++;

        service = joueur1;
        pointjoueur1 = 0;
        pointjoueur2 = 0;
        nombreseptjoueur1 = 1;
        nombreseptjoueur2 = 1;
        pointseptjoueur1 =0;
        pointseptjoueur2 = 0;
        pourcentage = tableau[joueur1.getCategorie()][joueur2.getCategorie()];
        gagnant = null;
        chronometre = new Chronometre();
        joueurservice = joueur1;

    }

    public int incrementerPoint(int pointjoueur) {
        if (pointjoueur == 0) {
            return 15;
        }
        if (pointjoueur == 15) {
            return 30;
        }
        if (pointjoueur == 30) {
            return 40;
        }
        if (pointjoueur == 40) {
            return 0;
        }
        return -1;
    }

    public void incrementerPoint1Joueur1() {
        pointjoueur1 = incrementerPoint(pointjoueur1);
        tableauPointAjoutValeurP(pointjoueur1, pointjoueur2);
        if (pointjoueur1 == 0) {
            pointjoueur2=0;
            incremeterPointSeptJoueur1();
            nomJoueurEvenement = joueur1.getNom();
            nombreevenement++;
        }
    }


    public void incrementerPoint2Joueur2() {
        pointjoueur2 = incrementerPoint(pointjoueur2);
        tableauPointAjoutValeurP(pointjoueur1, pointjoueur2);
        if (pointjoueur2 == 0) {
            pointjoueur1=0;
            incremeterPointSeptJoueur2();
            nomJoueurEvenement = joueur2.getNom();
            nombreevenement++;
        }
    }

    public int incrementerPointSept(int pointsept){
        if(pointsept ==5) {
            return 0;
        }else{
            pointsept++;
            return pointsept;
        }
    }

    public void incremeterPointSeptJoueur1(){
        pointseptjoueur1 = incrementerPointSept(pointseptjoueur1);

        if (pointseptjoueur1==0){
            tableauPointAjoutValeurSept(6, pointseptjoueur2);
            nombreseptjoueur1++;
            pointjoueur2=0;

            nbsept++;
            if (nbsept == 4){
                if((nombreseptjoueur2<nombreseptjoueur1)) {
                    gagnant = joueur1;
                }else{
                    gagnant = joueur2;
                }
                fin();
                //joueur1 GAGNE FIN du match
            }else{
                switchJoueurService();
            }

        }else{
            tableauPointAjoutValeurSept(pointseptjoueur1, pointseptjoueur2);
        }

    }

    public void tableauPointAjoutValeurSept(int x0 ,int x1 ){
        int[] table = {x0, x1};
        if(nbsept<4){
            grille[nbsept] = table;
        }

    }

    public void tableauPointAjoutValeurP(int x0 ,int x1 ){
        int[] table = {x0, x1};
        grille[0] = table;
    }

    public void incremeterPointSeptJoueur2(){
        pointseptjoueur2 = incrementerPointSept(pointseptjoueur2);

        if (pointseptjoueur2==0){
            tableauPointAjoutValeurSept(pointseptjoueur1,6);
            nombreseptjoueur2++;
            pointjoueur1=0;

            nbsept++;

            if (nbsept== 4){
                if(nombreseptjoueur1<nombreseptjoueur2) {
                    gagnant = joueur2;

                } else{
                    gagnant = joueur1;
                }
                fin();

                //joueur1 GAGNE FIN du match

            }else{
                switchJoueurService();
            }

        }else{
            tableauPointAjoutValeurSept(pointseptjoueur1, pointseptjoueur2);
        }
    }

    public void pointGagne(){

       int nombre =  Aleatoire.nombreAleatoireInt(0,100);
       if (nombre>pourcentage){
          // System.out.println("Point Pour Joueur2");
           incrementerPoint2Joueur2();
       }else{
          // System.out.println("Point Pour Joueur1");
           incrementerPoint1Joueur1();
       }
    }

   // @Override
    public void run() {

        chronometre.start();
        while((nbsept<4)&&(!fin)){

          //  System.out.println(toString());
            try {
             //   System.out.println("[THREAD: DORS]");
                Thread.sleep((long)Aleatoire.nombreAleatoireInt(2000, 5000));
                pointGagne();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       // chronometre.fin();
        //fin du chrono
    }


    public String tableauPointToString(){
        String tmp ="\n[POINT\n";

        for (int i = 0; i<2; i++){

            tmp+="JOUEUR"+(i+1);

            for (int j=0; j<4; j++){
                tmp+="  "+grille[j][i];
            }
            tmp+="\n";
        }
        return tmp;
    }
    public String toString() {
        return "[MATCH ID: "+machtid+"\nSept: "+nbsept+"\n Joueur1 catégorie: "+joueur1.getCategorie()+"\nJoueur2 catégorie:"+joueur2.getCategorie()+"\n"+tableauPointToString();
    }

    public static int[][] getTableau() {
        return tableau;
    }

    public static int getNombredematch() {
        return nombredematch;
    }

    public int getMatchId() {
        return machtid;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Joueur getService() {
        return service;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

    public int getPointjoueur1() {
        return pointjoueur1;
    }

    public int getPointjoueur2() {
        return pointjoueur2;
    }

    public int getNombreseptjoueur1() {
        return nombreseptjoueur1;
    }

    public int getNombreseptjoueur2() {
        return nombreseptjoueur2;
    }

    public int getPointseptjoueur1() {
        return pointseptjoueur1;
    }

    public int getPointseptjoueur2() {
        return pointseptjoueur2;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public int getNbsept() {
        return nbsept;
    }

    public int[][] getGrille() {
        return grille;
    }

    public boolean isFin() {
        return fin;
    }

    public void fin(){

        joueur1.setUtilise(false);
        joueur1.setUtilise(false);
        listejoueur.setJoueurDisponible(joueur1);
        listejoueur.setJoueurDisponible(joueur2);
        chronometre.fin();
        fin = true;

       // System.out.println("FIN");
       // System.out.println("le gagnant est:"+ gagnant.getCategorie());

    }

    public int getMinute(){
        return this.chronometre.getMinute();
    }

    public int getSeconde(){
        return this.chronometre.getSeconde();
    }

    public int getIdJoueurGagnant(){
        if(this.gagnant == null){
            return -1;
        }else{
            return this.gagnant.getIdJoueur();
        }
    }

    public void ajouterMiseJoueur(int idjoueur, float montant){
        if(idjoueur==joueur1.getIdJoueur()){
            misejoueur1+=montant;
            nbparijoueur1++;
        }
        if(idjoueur==joueur2.getIdJoueur()){
            misejoueur2+=montant;
            nbparijoueur2++;
        }
    }

    public float montantgagne(int idjoueur){
        if(gagnant==null){
            return 0;
        }
        float montanttotalegagnant=0;
        int nbparieurs=1;
        if(gagnant.getIdJoueur()==joueur1.getIdJoueur()){
            montanttotalegagnant = misejoueur1;
            nbparieurs = nbparijoueur1;

        }
        if(gagnant.getIdJoueur()==joueur2.getIdJoueur()){
            montanttotalegagnant = misejoueur2;
            nbparieurs = nbparijoueur2;
        }

        if(idjoueur!=gagnant.getIdJoueur()){
            return -1;
        }else{
            float gain= (float) (montanttotalegagnant*(0.75)/nbparieurs);

            return gain;
        }
    }
    public String getNomGagnant(){
        if(gagnant==null){
            return "Aucun";
        }
        return gagnant.getNom();
    }

    public void switchJoueurService(){
        if(joueurservice==joueur1){
            joueurservice = joueur2;
        }else{
            joueurservice = joueur1;
        }
    }

    public Joueur getJoueurservice() {
        return joueurservice;
    }

    public int getNombreevenement() {
        return nombreevenement;
    }

    public String getNomJoueurEvenement() {
        return nomJoueurEvenement;
    }

    public boolean test_IncrementerPoint(){
        int a = 0;
        int b = 15;
        int c = 30;
        int d = 40;

        int resultat = incrementerPoint(0);
        if(resultat != 15){
            return false;
        }

        resultat = incrementerPoint(15);
        if(resultat != 30){
            return false;
        }

        resultat = incrementerPoint(30);
        if(resultat != 40){
            return false;
        }

        resultat = incrementerPoint(40);
        if(resultat != 0){
            return false;
        }

        return true;
    }

}









