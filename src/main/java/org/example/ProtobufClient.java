package org.example;

import org.example.PersonMessage.PersonProto;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProtobufClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;

        PersonProto person = PersonProto.newBuilder()
                .setName("Kelvin")
                .setAge(22)
                .setCity("Fortaleza")
                .build();

        try {
            byte[] serializedData = person.toByteArray();
            int dataLength = serializedData.length;

            Socket socket = new Socket(host, port);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            dos.write(serializedData);
            System.out.println("Sent serialized Protobuf data. Size: " + dataLength + " bytes");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

