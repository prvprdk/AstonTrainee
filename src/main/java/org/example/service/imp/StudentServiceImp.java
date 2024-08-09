package org.example.service.imp;

import org.example.entity.Student;
import org.example.repo.StudentRepo;
import org.example.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void add(Student student) {
        studentRepo.add(student);
    }

    @Override
    public void update(int id, Student student) {
        studentRepo.update(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepo.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
}
