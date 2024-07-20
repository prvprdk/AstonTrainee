package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    int id;
    String name;
    //OneToMany
    Set<LearnClass> classes;

    public Direction(int id, String name) {
        this.id = id;
        this.name = name;

    }

}
