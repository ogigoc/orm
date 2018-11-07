package entities;

import annotations.*;

@Table(name="Students")
public class Student {

    @Column(name="id")
    @Id
    @NotNull
    @AutoIncrement
    private int id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="index")
    private String index;

    public Student(String name, String index) {
        this.name = name;
        this.index = index;
    }

    @PostInsert
    private void logInsertion() {
        System.out.printf("Inserted Student with id: %d, name: %s, index: %s into database\n", id, name, index);

    }

    public void print() {
        System.out.printf("Student id: %d name: %s\n", id, name);
    }
}
