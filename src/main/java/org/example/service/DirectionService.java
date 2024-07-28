package org.example.service;

import org.example.entity.Direction;

import java.util.List;
import java.util.Optional;

public interface DirectionService {
    void add(Direction direction);

    void update(int id, Direction direction);

    void delete(int id);

    Optional <Direction> findById(int id);
    List<Direction> findALl();
}
