package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class XmlClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;

//        Person person = new Person("Kelvin", 22, "Fortaleza");

        Person[] students = {
                new Person("Ze colmeia", 13, "Pirambu"),
                new Person("Catatau", 17, "Carandiru"),
                new Person("Du Dudu Edu", 24, "Rocinha")
        };
        Classroom c = new Classroom("Performance, Cibersegurança e Computação", students);

        try {
            // Serialize object to XML
            XmlMapper xmlMapper = new XmlMapper();
            String xmlData = xmlMapper.writeValueAsString(c);
            byte[] xmlBytes = xmlData.getBytes();

            // Calculate the size of the serialized XML data
            int serializedSize = xmlBytes.length;

            // Send the XML data over a socket
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Send the XML data
            dataOutputStream.write(xmlBytes);

            System.out.println("Serialized XML size: " + serializedSize + " bytes");
            System.out.println("Sent XML data:\n" + xmlData);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
