package org.example;

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

public class SerializationClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;
        Person person = new Person("Kelvin", 22, "Fortaleza");

        try {
            // Calcular tamanho em bytes
            ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
            ObjectOutputStream sizeStream = new ObjectOutputStream(byteArrayStream);
            sizeStream.writeObject(person);
            sizeStream.flush();
            int serializedSize = byteArrayStream.size();
            sizeStream.close();

            // Enviar objeto para o servidor
            Socket socket = new Socket(host, port);
            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(person);
            System.out.println("Serialized object size: " + serializedSize + " bytes");
            System.out.println("Object sent: " + person);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

