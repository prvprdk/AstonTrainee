package org.example.service;

import org.example.entity.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentService extends Service <Student, Integer> {

}
