package org.example;

import org.example.PersonMessage.PersonProto;
import org.example.ClassroomMessage.ClassroomProto;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ProtobufClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;

//        PersonProto person = PersonProto.newBuilder()
//                .setName("Kelvin")
//                .setAge(22)
//                .setCity("Fortaleza")
//                .build();

        PersonProto student1 = PersonProto.newBuilder().setName("Ze colmeia").setAge(13).setCity("Pirambu").build();
        PersonProto student2 = PersonProto.newBuilder().setName("Catatau").setAge(17).setCity("Carandiru").build();
        PersonProto student3 = PersonProto.newBuilder().setName("Du Dudu Edu").setAge(24).setCity("Rocinha").build();
        List<PersonProto> students = Arrays.asList(student1, student2, student3);
        ClassroomProto classroom = ClassroomProto.newBuilder()
                .setName("Performance, Cibersegurança e Computação")
                .addAllStudents(students)
                .build();

        try {
            byte[] serializedData = classroom.toByteArray();
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

