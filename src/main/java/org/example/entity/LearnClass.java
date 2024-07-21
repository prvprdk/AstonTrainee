package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnClass {
    private int id;
    private String name;
    // ManyToOne
    private Direction direction;
    private Set<Student> students;

    public LearnClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
