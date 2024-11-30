package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JsonServer {
    public static void main(String[] args) {
        int port = 9999;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                DataInputStream dis = new DataInputStream(socket.getInputStream());

                // Read the length of the JSON data (4 bytes)
                int dataLength = dis.readInt();
                System.out.println("Expected JSON size: " + dataLength + " bytes");

                // Read the JSON data
                byte[] dataBuffer = new byte[dataLength];
                dis.readFully(dataBuffer);
                String jsonData = new String(dataBuffer);
                System.out.println("Received JSON data: " + jsonData);

                // Deserialize the JSON back into a Person object
                ObjectMapper objectMapper = new ObjectMapper();
                Person person = objectMapper.readValue(jsonData, Person.class);
                System.out.println("Deserialized object: " + person);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}