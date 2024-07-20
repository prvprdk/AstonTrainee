package org.example.service;

import org.example.entity.Direction;
import org.example.servlets.payload.DirectionUpdate;

import java.util.Set;

public interface DirectionService {

    Set<Direction> findAll();

    void add(Direction direction);

    Direction findById(int id);

    void deleteById(int id);

    void update(DirectionUpdate update);

}
