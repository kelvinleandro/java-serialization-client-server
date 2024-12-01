package org.example;

import org.example.PersonMessage.PersonProto;
import org.example.ClassroomMessage.ClassroomProto;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProtobufServer {
    public static void main(String[] args) {
        int port = 9999;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept client connection
                Socket socket = serverSocket.accept();
                System.out.println("Connected to client.");

                // Read data from the client
                InputStream inputStream = socket.getInputStream();

                // Parse the Protobuf message directly from the InputStream
//                PersonProto obj = PersonProto.parseFrom(inputStream);
                ClassroomProto obj = ClassroomProto.parseFrom(inputStream);

                // Print the deserialized Person object
                System.out.println("Deserialized object: " + obj);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
