package org.example.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(name = "pet_seq",
            sequenceName = "global_seq ",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany()
    @JoinTable(name = "audience_student",
            joinColumns = @JoinColumn(name = "id_student", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_audience", referencedColumnName = "id")
    )
    private List<Audience> classes;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Audience> getClasses() {
        return classes;
    }

    public void setClasses(List<Audience> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
