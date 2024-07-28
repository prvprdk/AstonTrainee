package org.example.service.imp;

import org.example.entity.Student;
import org.example.repo.StudentRepo;
import org.example.repo.imp.StudentRepoImp;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo = new StudentRepoImp();

    @Override
    public void add(Student student) {
        studentRepo.add(student);
    }

    @Override
    public void update(int id, Student updateStudent) {
        studentRepo.update(updateStudent);
    }

    @Override
    public void delete(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepo.findById(id);
    }

    @Override
    public List<Student> findALl() {
        return studentRepo.findAll();
    }
}
