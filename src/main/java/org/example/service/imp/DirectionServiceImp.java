package org.example.service.imp;

import org.example.entity.Direction;
import org.example.repo.DirectionRepo;
import org.example.repo.imp.DirectionRepoImp;
import org.example.service.DirectionService;

import java.util.List;
import java.util.Optional;

public class DirectionServiceImp implements DirectionService {

    private final DirectionRepo directionRepo = new DirectionRepoImp();


    @Override
    public void add(Direction direction) {
        directionRepo.add(direction);
    }

    @Override
    public void update(int id, Direction updateDirection) {

        directionRepo.update(updateDirection);

    }

    @Override
    public void delete(int id) {

        directionRepo.deleteById(id);
    }

    @Override
    public Optional<Direction> findById(int id) {
        return directionRepo.findById(id);
    }

    @Override
    public List<Direction> findALl() {
        return directionRepo.findAll();
    }
}
