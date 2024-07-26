package org.example.utils;

import org.example.entity.Audience;
import org.example.entity.Direction;
import org.example.entity.Student;

import java.io.PrintWriter;
import java.util.List;

public class Printer {

    public static void print(Direction direction, PrintWriter printWriter) {
        printWriter.println(direction.getNameDirection());
        direction.getAudiences().forEach(a -> printWriter.println(a.getName()));
    }

    public static void print(Student student, PrintWriter printWriter) {
        printWriter.println(student.getName());
        student.getClasses().forEach(a -> printWriter.println(a.getName()));

    }

    public static void print(Audience audience, PrintWriter printWriter) {
        printWriter.println(audience.getName());
        audience.getStudents().forEach(a -> printWriter.println(a.getName()));
    }

    public static void printAudi(List<Audience> audiences, PrintWriter printWriter) {
        audiences.forEach(a -> printWriter.println(a.getName()));
    }

    public static void printStudent(List<Student> students, PrintWriter printWriter) {
        students.forEach(a -> printWriter.println(a.getName()));
    }

    public static void print(List<Direction> audiences, PrintWriter printWriter) {
        audiences.forEach(a -> printWriter.println(a.getNameDirection()));
    }


}
