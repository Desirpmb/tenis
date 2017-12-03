package com.company;

import javax.xml.ws.Service;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur extends Thread {

    private ListeMatch listematch;
    private ListeJoueur listeJoueur;
    private DatagramSocket dataSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(20);
    private int port;
    private ServiceParis serviceparis;


    public Serveur(int port){
        this.listeJoueur = new ListeJoueur();
        this.listematch = new ListeMatch();
        MatchBuilder builder = new MatchBuilder(listeJoueur);
        this.serviceparis = new ServiceParis();

        try {
            this.dataSocket = new DatagramSocket(port);
            this.port = port;
            System.out.println("[SERVEUR] IP: "+ InetAddress.getByName("localhost").toString());

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
            initialisationListeMatch(builder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void initialisationListeMatch(MatchBuilder builder){


        for (int i = 0 ; i<7; i++){
            Match match = builder.creationMatch();
            listematch.addMatch(match);
        }

    }


    public void run(){

        while(true) {

            byte[] buffer = new byte[8192];


            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);


            try {
                System.out.println("[SERVEU] Attend requête... ");
                dataSocket.receive(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[SERVEU] requête reçu ");
            Repondeur repondeur = new Repondeur(this, packet);
            executor.execute(repondeur);
        }

    }

    public void envoyerDatagramPacket(DatagramPacket packet){
        System.out.println("[SERVEU] envoi vers le client ");
        try {
            dataSocket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServiceParis getServiceparis() {
        return serviceparis;
    }

    public ListeMatch getListematch() {
        return listematch;
    }

    public ListeJoueur getListeJoueur() {
        return listeJoueur;
    }

    public DatagramSocket getDataSocket() {
        return dataSocket;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setListematch(ListeMatch listematch) {
        this.listematch = listematch;
    }

    public void setListeJoueur(ListeJoueur listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    public void setDataSocket(DatagramSocket dataSocket) {
        this.dataSocket = dataSocket;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

}
