package org.example;

import java.io.Serializable;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Classroom implements Serializable {
    String name;
    Person[] students;
    char numberStudents;

    public Classroom(String name, Person[] students) {
        this.name = name;
        this.students = students;
        this.numberStudents = (char) students.length;
    }

    public Person[] getStudents() {
        return students;
    }

    public void setStudents(Person[] students) {
        this.students = students;
        this.numberStudents = (char) students.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getNumberStudents() {
        return numberStudents;
    }

//    public static void main(String[] args) {
//        Person[] students = {
//                new Person("Ze colmeia", 13, "Fortaleza"),
//                new Person("Catatau", 17, "Floresta"),
//                new Person("Du Dudu Edu", 24, "Rocinha")
//        };
//        Classroom c = new Classroom("Primeiro Pici da Cidade", students);
//    }
}
