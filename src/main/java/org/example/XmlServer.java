package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class XmlServer {
    public static void main(String[] args) {
        int port = 9999;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept client connection
                Socket socket = serverSocket.accept();
                System.out.println("Connected to client.");

                // Read the XML data from the client
                InputStream inputStream = socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                byte[] buffer = new byte[1024];
                int bytesRead;

                // Read the input stream in chunks
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    stringBuilder.append(new String(buffer, 0, bytesRead));
                }

                String xmlData = stringBuilder.toString();
                System.out.println("Received XML data:\n" + xmlData);

                // Deserialize XML into a Person object
                XmlMapper xmlMapper = new XmlMapper();
//                Person obj = xmlMapper.readValue(xmlData, Person.class);
                Classroom obj = xmlMapper.readValue(xmlData, Classroom.class);

                // Print the deserialized Person object
                System.out.println("Deserialized object: " + obj);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
