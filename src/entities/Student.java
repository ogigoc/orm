package entities;

import annotations.*;

@Table(name="Students")
public class Student {

    @Column
    @Id
    @NotNull
    @AutoIncrement
    private int id;

    @Column
    private String name;

    @Column
    private String index;

    public Student(String name, String index) {
        this.name = name;
        this.index = index;
    }

}
