package com.company;

import seriali.Message;

import java.net.DatagramPacket;
import java.util.Collection;


public class Repondeur implements Runnable  {
    private Serveur serveur;
    private DatagramPacket packet;


    public Repondeur(Serveur serveur, DatagramPacket packet){
        this.serveur = serveur;
        this.packet = packet;
    }

    public void run() {
        System.out.println("[Repondeur] requête reçu ");
        SerializerMessage serializerMessage = new SerializerMessage();
        Message message = serializerMessage.deserializer(packet.getData());

       Message reponse = traitement(message);


        if(reponse.getCode() >= 0){
            byte[] buffer = serializerMessage.serializer(reponse);
            DatagramPacket packetreponse = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
            serveur.envoyerDatagramPacket(packetreponse);

        }


    }

    public Message traitement(Message message){
        int code = message.getCode();

        Message reponse = null;
        int nbmatch = serveur.getListematch().getListe().size();
        String donnee[][];


        if(code==1){
            donnee = new String[nbmatch][25];
            int i=0;
            Collection<Match> values = serveur.getListematch().getListe().values();
            for (Match match : values){
                donnee[i][0] = ""+match.getMatchId();
                donnee[i][1] = ""+match.getJoueur1().getNom();
                donnee[i][2] = ""+match.getJoueur1().getPrenom();
                donnee[i][3] = ""+match.getJoueur2().getNom();
                donnee[i][4] = ""+match.getJoueur2().getPrenom();
                donnee[i][5] = ""+match.getMinute();
                donnee[i][6] = ""+match.getSeconde();

                int[][] point = match.getGrille();
                int k =7;
                for(int j = 0; j<4; j++){

                    donnee[i][k] = ""+point[j][0];
                    k++;
                }

                for(int j = 0; j<4; j++){

                    donnee[i][k] = ""+point[j][1];
                    k++;
                }
                donnee[i][15] = ""+match.getNbsept();
                donnee[i][16] =""+match.getNomGagnant();
                donnee[i][17]= ""+match.getJoueur1().getIdJoueur();
                donnee[i][18]=""+match.getJoueur2().getIdJoueur();
                donnee[i][19]=match.getJoueurservice().getNom();
                donnee[i][21]=""+match.getNombreevenement();
                donnee[i][20]=""+match.getNomJoueurEvenement();
                i++;
            }

            reponse= new Message(1, donnee);
            //conditions

            return reponse;
        }
        if(code==2){
            System.out.println("[Repondeur] requête Pari Reçu");
            donnee = new String[1][1];
            String donneerecu[][] = message.getDonnee();

            int id = Integer.parseInt(donneerecu[0][0]);
            int montant = Integer.parseInt(donneerecu[0][1]);
            int idjoueur = Integer.parseInt(donneerecu[0][2]);

            if (serveur.getListematch().verificationExistant(id, idjoueur)) {

                Match match = serveur.getListematch().getMatch(id);
                Joueur joueur = serveur.getListeJoueur().getJoueurByID(idjoueur);


                Pari pari = new Pari(montant, match, joueur);


                int idpari = serveur.getServiceparis().addPari(pari);
                match.ajouterMiseJoueur(joueur.getIdJoueur(), montant);

                donnee[0][0] = "" + idpari;
                System.out.println("[Repondeur] Pari validé: [idmatch= "+id+" idjoueur: "+idjoueur+" montant= "+montant);
            }else{
                donnee[0][0] = "-1";
                System.out.println("[Repondeur] Pari invalidé: [idmatch= "+id+" idjoueur: "+idjoueur+" montant= "+montant);
            }

            reponse= new Message(2, donnee);
            //conditions

            return reponse;

        }

        if (code==0){
            System.out.println("type 0\n");
            String[][] donneerecu = message.getDonnee();

            int nbparigagne =0;

            for (int i =0; i<donneerecu.length; i++){
                int idparis = Integer.parseInt(donneerecu[i][0]);
                float gain = serveur.getServiceparis().getMontantGain(idparis);
                if(gain>0){
                    String sgain = ""+gain;
                   donneerecu[i][1]= sgain;
                   donneerecu[i][2] = ""+2;
                }if (gain==-1){
                    donneerecu[i][2]=""+0;
                }
            }
            reponse = new Message(0, donneerecu);
            return reponse;

        }

        return reponse;
    }




}
