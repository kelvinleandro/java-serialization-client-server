package org.example;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerializationServer {
    public static void main(String[] args) {
        int port = 9999;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting ...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            Object receivedObject = ois.readObject();
//
//            if (receivedObject instanceof Classroom) {
//                Classroom obj = (Classroom) receivedObject;
//                System.out.println("Received object: " + obj);
//            } else {
//                System.out.println("Received invalid object: " + receivedObject);
//            }

            // receiving as separate fields
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            String name = dataInputStream.readUTF();
//            int age = dataInputStream.readInt();
//            String city = dataInputStream.readUTF();
//
//            System.out.println("Received data:");
//            System.out.println("Name: " + name);
//            System.out.println("Age: " + age);
//            System.out.println("City: " + city);

            String classroomName = dataInputStream.readUTF();
            System.out.println("Received classroom name: " + classroomName);

            int numberOfStudents = dataInputStream.readInt();
            System.out.println("Received number of students: " + numberOfStudents);

            for (int i = 0; i < numberOfStudents; i++) {
                String studentName = dataInputStream.readUTF();
                int studentAge = dataInputStream.readInt();
                String studentCity = dataInputStream.readUTF();

                System.out.println("Received student:");
                System.out.println("  Name: " + studentName);
                System.out.println("  Age: " + studentAge);
                System.out.println("  City: " + studentCity);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
