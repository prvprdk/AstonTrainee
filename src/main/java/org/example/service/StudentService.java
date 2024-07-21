package org.example.service;

import org.example.entity.Student;
import org.example.servlets.payload.UpdateStudent;

import java.util.Set;

public interface StudentService {

    void add(Student student);

    Set<Student> findAll();

    Student findById(int id);

    void deleteById(int id);

    void update(UpdateStudent update);
}
