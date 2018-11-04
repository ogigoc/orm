package main;

import entities.Student;
import entities.TestEntity;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Pera", "43");
        Student s2 = new Student("Mika", "52");


        System.out.println(DBManager.getAll(Student.class));
    }
}
