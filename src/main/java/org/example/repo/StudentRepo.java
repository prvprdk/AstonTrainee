package org.example.repo;

import org.example.entity.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepo extends Repository<Student, Integer> {

}
