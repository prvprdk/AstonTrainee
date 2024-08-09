package org.example.service.imp;

import org.example.entity.Direction;
import org.example.repo.DirectionRepo;
import org.example.service.DirectionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DirectionServiceImp implements DirectionService {


    private final DirectionRepo directionRepo;

    public DirectionServiceImp(DirectionRepo directionRepo) {
        this.directionRepo = directionRepo;
    }


    @Override
    public void add(Direction direction) {
        directionRepo.add(direction);
    }

    @Override
    public void update(int id, Direction updateDirection) {

        directionRepo.update(updateDirection);

    }

    @Override
    public void deleteById(int id) {

        directionRepo.deleteById(id);
    }

    @Override
    public Optional<Direction> findById(int id) {
        return directionRepo.findById(id);
    }

    @Override
    public List<Direction> findAll() {
        return directionRepo.findAll();
    }
}
