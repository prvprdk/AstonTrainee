package org.example.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @SequenceGenerator(name = "pet_seq",
            sequenceName = "global_seq ",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String nameDirection;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "direction")
    private List<Audience> audiences;

    public Direction() {
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<Audience> audiences) {
        this.audiences = audiences;
    }

    public String getNameDirection() {
        return nameDirection;
    }

    public void setNameDirection(String nameDirection) {
        this.nameDirection = nameDirection;
    }


}
