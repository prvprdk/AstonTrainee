package org.example.service;

import org.example.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void add(Student student);

    void update(int id, Student student);

    void delete(int id);

    Optional<Student> findById(int id);

    List<Student> findALl();
}
