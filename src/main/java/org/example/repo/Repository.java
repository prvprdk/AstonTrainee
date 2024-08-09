package org.example.repo;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<T, R> {

    void add(T t);

    void update(T t);

    void deleteById(R r);

    Optional<T> findById(R r);

    List<T> findAll();
}
