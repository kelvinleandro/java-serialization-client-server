package org.example;

import java.io.Serializable;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Classroom")
public class Classroom implements Serializable {
    String name;
    Person[] students;
    int numberStudents;

    public Classroom() {}

    public Classroom(String name, Person[] students) {
        this.name = name;
        this.students = students;
        this.numberStudents = students.length;
    }

    public Person[] getStudents() {
        return students;
    }

    public void setStudents(Person[] students) {
        this.students = students;
        this.numberStudents = students.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    @Override
    public String toString() {
        return "Classroom: " + this.name + ", Students: " + this.numberStudents;
    }
}
