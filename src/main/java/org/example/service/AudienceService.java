package org.example.service;

import org.example.entity.Audience;

import java.util.List;
import java.util.Optional;

public interface AudienceService {
    void add(Audience direction);

    void update(int id, Audience direction);

    void delete(int id);

    Optional<Audience> findById(int id);
    List<Audience> findAll();
}
