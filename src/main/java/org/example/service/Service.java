package org.example.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, R> {

    void add(T t);

    void update(int id, T t);

    void deleteById(int R);

    Optional<T> findById(int R);

    List<T> findAll();
}
