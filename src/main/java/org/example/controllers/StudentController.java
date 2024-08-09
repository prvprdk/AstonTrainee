package org.example.controllers;


import org.example.entity.DistantStudent;
import org.example.entity.LocalStudent;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("distant")
    public void add(DistantStudent distantStudent) {
        studentService.add(distantStudent);
    }

    @PostMapping("local")
    public void add(LocalStudent localStudent) {
        studentService.add(localStudent);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

}
