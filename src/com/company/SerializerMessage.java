package com.company;

import seriali.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializerMessage {

	public SerializerMessage(){

	}
	public  byte[] serializer(Message message){
		   try{
			   ByteArrayOutputStream bytetab = new ByteArrayOutputStream();
	           ObjectOutputStream stream = new ObjectOutputStream(bytetab);
	           stream.writeObject(message);
	           
	           return bytetab.toByteArray();
	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   return null;
	}
	
	public  Message deserializer(byte[] bytetab ){
		try {
			final ByteArrayInputStream bytes = new ByteArrayInputStream(bytetab);
			ObjectInputStream stream = new ObjectInputStream(bytes);
			return (Message) stream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
		return null;
	}

	public void marshal(Message message) {
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance( "com.acme.foo" );

			Unmarshaller u = jc.createUnmarshaller();
			Marshaller m = jc.createMarshaller();

			DOMResult result = new DOMResult();

			m.marshal( message, result );

		} catch (JAXBException e) {
			e.printStackTrace();
		}


	}
	
}
