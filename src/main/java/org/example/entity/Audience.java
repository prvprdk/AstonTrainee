package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "audience")
public class Audience {
    @Id

    @SequenceGenerator(name = "pet_seq",
            sequenceName = "global_seq ",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    private Direction direction;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students;

    public Audience() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
