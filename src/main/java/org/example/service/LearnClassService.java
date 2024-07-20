package org.example.service;

import org.example.entity.LearnClass;
import org.example.servlets.payload.LearnClassUpdate;

public interface LearnClassService {

    void add(LearnClass learnClass);

    void addStudentToClass(int idClass, int idStudent);

    LearnClass findById(int id);

    void deleteById(int id);

    void update(LearnClassUpdate update);

}
