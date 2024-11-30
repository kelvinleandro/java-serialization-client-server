package org.example;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerializationServer {
    public static void main(String[] args) {
        int port = 9999;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexao ...");

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object receivedObject = ois.readObject();

            if (receivedObject instanceof Person) {
                Person person = (Person) receivedObject;
                System.out.println("Objeto recebido: " + person);
            } else {
                System.out.println("Objeto recebido não é uma instância de Person.");
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
