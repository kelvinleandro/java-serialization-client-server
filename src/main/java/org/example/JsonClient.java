package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.net.Socket;

public class JsonClient {
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
            // Serialize object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(c);
            byte[] jsonBytes = jsonData.getBytes();

            // Calculate the size of the serialized JSON data
            int dataLength = jsonBytes.length;

            // Create a socket and send the data
            Socket socket = new Socket(host, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Send the length of the JSON first (4 bytes)
            dos.writeInt(dataLength);

            // Send the JSON data
            dos.write(jsonBytes);
            System.out.println("Sent JSON data: " + jsonData);
            System.out.println("Serialized JSON size: " + dataLength + " bytes");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
