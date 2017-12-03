package com.company;

import seriali.Message;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Client  extends Thread{
    private HashMap<Integer, Integer> listeidparis;
    private DatagramSocket datagramSocket;
    private InetAddress adresseServeur;
    private int port;
    private int portServeur;
    private boolean quitter = false;


    public  Client(int port, String adresseServeur, int portServeur){

        listeidparis = new HashMap<>();
        try {
            this.datagramSocket = new DatagramSocket(port);
            this.port = port;
            this.portServeur = portServeur;
            try {
                this.adresseServeur = InetAddress.getByName(adresseServeur);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {



        Thread  verifpari = new Thread(){
            public void run() {
                while (!quitter){
                    try {
                        Thread.sleep(3000);
                        if(listeidparis.size()>0) {
                         //  System.err.println("message gagna?");
                            envoyerReceptionnerTraiter(verificationParisGagnant());

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        verifpari.start();

        Message message;
        while(!quitter) {
            int principal = menus();

            if (principal == 1) {

                envoyerReceptionnerTraiter(listeMatchMessage(),"Chargement de la liste des match...");


            } else if (principal == 2) {
                envoyerReceptionnerTraiter(menusPariMessage(),"Attente de Validation de votre pari par le Serveur...");


            } else if (principal == 3){
                envoyerReceptionnerTraiter(verificationParisGagnant(),"Liste des PariS gagnés:");

            } else if (principal == 4) {
                quitter = true;
            }

        }

    }

    public void removePari(int id){
       listeidparis.remove(id);

    }

    public synchronized Message traitement(Message message){

        Message reponse = new Message();
        String[][] donnee = message.getDonnee();

        if(message.getCode() == 1){
            String tmp="";
            for (int i =0; i<donnee.length ; i++){
                tmp+="\n[MatchID :"+donnee[i][0];
                tmp+="\nNom: "+donnee[i][1]+" Prenom: "+donnee[i][2]+" (ID "+donnee[i][17]+")";
                tmp+="\nNom: "+donnee[i][3]+" Prenom: "+donnee[i][4]+" (ID "+donnee[i][18]+ ")";
                tmp+="\n\n     Point\n";
                tmp+= donnee[i][1].substring(0,3)+": "+donnee[i][7]+" "+donnee[i][8]+" "+donnee[i][9]+ " " +donnee[i][10];
                tmp+= "\n"+donnee[i][3].substring(0,3)+": "+donnee[i][11]+" "+donnee[i][12]+" "+donnee[i][13]+ " " +donnee[i][14];
                tmp+="\n\nSept numéro "+donnee[i][15];
                tmp+="\nService: "+donnee[i][19];
                tmp+="\nChrono: "+donnee[i][5]+":"+donnee[i][6]+"";
                tmp+="\nGagnant: "+donnee[i][16];
                tmp+="\n]\n\n";
            }
            System.out.println(tmp);
            return reponse;
        }

        if (message.getCode()==2){

            int idparis = Integer.parseInt(donnee[0][0]);
            if(idparis<0){
                System.out.println("\n Désolé votre pari est invalide\n");
                return reponse;

            }
            listeidparis.put(idparis,idparis);
            System.out.println("\n Votre pari a été validé et enregistré sous l'ID:"+idparis);
            return reponse;
        }

        if (message.getCode()==0){
            String notification ="\n[[Notifaction: ";
            boolean notif = false;


            for(int i =0; i<donnee.length; i++){
                int etat = Integer.parseInt(donnee[i][2]);
                if(etat==0) {
                    listeidparis.remove(Integer.parseInt(donnee[i][0]));
                }else if(etat==2) {
                    notif=true;
                    listeidparis.remove(Integer.parseInt(donnee[i][0]));
                    notification += "\n- Félicitation vous avez gagné le pari ID: " + donnee[i][0] + " le montant de " + donnee[i][1] + " vous sera viré.\n";

                }
            }
            if(notif) {
                System.out.println(notification);
                return new Message(10,null);
            }



        }

       return reponse;

    }
    public int demande(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println('\n'+message+'\n');
        int reponse = sc.nextInt();

        return reponse;
    }

    public int menus(){
        boolean valide = false;
                int principale = 0;
                while(!valide){
                    principale = demande("****Menu Principal******\n-Tapez 1: afficher Liste des matchs ou Rafrechir\n-Tapez 2: Parier\n-Tapez 3: Pour verifier vos paris gagant\n-Tapez 4: Pour Quitter\n");

                    if((principale==1)||(principale==2)||(principale==3)||(principale==4)){
                        valide= true;
                    }
                }

        return principale;
    }



    public Message menusPariMessage(){

        String[][] donnee = new String[1][3];
        int matchid = demande("*****Pari******\nEntrez ID du match svp:");
        int joueurid = demande("Entrez ID du Joueur svp: ");
        int montant = demande("Entrez le montant svp:");

        donnee[0][0]=""+matchid;
        donnee[0][1]=""+montant;
        donnee[0][2]=""+joueurid;

        Message message = new Message(2, donnee);
        return message;

    }

    public Message listeMatchMessage(){
        return new Message(1,null);
    }

    public synchronized void  receptiontraitement(){
        SerializerMessage serializerMessage = new SerializerMessage();
        byte[] buffer = new byte[10000];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
           // System.out.println("Reception requête...");
            datagramSocket.receive(packet);
            //System.out.println(" reçu");
            Message message = serializerMessage.deserializer(packet.getData());
            Message reponse = traitement(message);

        } catch (IOException e) {
            System.out.println("PB");
            e.printStackTrace();
        }
    }

    public synchronized void envoyerMessage(Message messageenvoyer){
        SerializerMessage serializerMessage = new SerializerMessage();

        byte[] dataenvoyer = serializerMessage.serializer(messageenvoyer);
        DatagramPacket packetenvoye = new DatagramPacket(dataenvoyer, dataenvoyer.length,adresseServeur,portServeur);

        try {
            //Envoi du paquet
          //  System.out.println("Envoi d'une requête");
            datagramSocket.send(packetenvoye);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("PBENVOI");
        }
    }

    public Message verificationParisGagnant(){

        String[][] donnee = new String[listeidparis.size()][3];

        Collection<Integer> values = listeidparis.values();
        int i=0;
        for(Integer idpari : values){
            donnee[i][0]=""+idpari;
            donnee[i][1]=""+0;
            donnee[i][2]=""+1;
            i++;
        }
        return new Message(0,donnee);
    }

    public synchronized void envoyerReceptionnerTraiter(Message message, String messageconsole){

        envoyerMessage(message);
        if (messageconsole != null) {
            System.out.println("\n" + messageconsole + "\n");
        }
        receptiontraitement();
    }

    public synchronized void envoyerReceptionnerTraiter(Message message){
        envoyerReceptionnerTraiter(message, null);
    }
}
