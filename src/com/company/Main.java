package com.company;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;

public class Main {

    public static void main(String[] args) {

       Serveur serveur = new Serveur(12000);

       serveur.start();



    }
}
