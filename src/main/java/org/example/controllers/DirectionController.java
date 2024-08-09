package org.example.controllers;

import org.example.entity.Direction;
import org.example.service.DirectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direction")
public class DirectionController {

    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }


    @GetMapping
    public List<Direction> findAll() {
        return directionService.findAll();
    }

    @GetMapping("/{id}")
    public Direction findById(@PathVariable("id") int id) {
        return directionService.findById(id).orElseThrow(() -> new NullPointerException("User not found"));

    }

    @PutMapping("/{id}")
    public void update(int id, Direction direction) {
        directionService.update(id, direction);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        directionService.deleteById(id);
    }

}
