package main;

import entities.Student;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Pera", "43");
        Student s2 = new Student("Mika", "52");

        EntityManager manager = new EntityManager();

        try {
            manager.getAll(Student.class);
            manager.insert(Student.class, s2);
            manager.get(Student.class, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
