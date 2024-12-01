package org.example;

import java.io.DataOutputStream;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

public class SerializationClient {
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
            // Size in bytes
//            ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
//            ObjectOutputStream sizeStream = new ObjectOutputStream(byteArrayStream);
//            sizeStream.writeObject(c);
//            sizeStream.flush();
//            int serializedSize = byteArrayStream.size();
//            sizeStream.close();

            // Sending object to the server
            Socket socket = new Socket(host, port);
//            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
//            stream.writeObject(c);
//            System.out.println("Serialized object size: " + serializedSize + " bytes");
//            System.out.println("Object sent: " + c);

            // Sending as separate fields
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            dataOutputStream.writeUTF(person.getName());
//            dataOutputStream.writeInt(person.getAge());
//            dataOutputStream.writeUTF(person.getCity());

            // Sending complex object as separate fields
            dataOutputStream.writeUTF(c.getName());
            Person[] studentsInClassroom = c.getStudents();
            dataOutputStream.writeInt(studentsInClassroom.length);
            for (Person student : studentsInClassroom) {
                dataOutputStream.writeUTF(student.getName());
                dataOutputStream.writeInt(student.getAge());
                dataOutputStream.writeUTF(student.getCity());
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
