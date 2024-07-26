package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.example.service.imp.StudentServiceImp;
import org.example.utils.Printer;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/student/*")
public class StudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (PrintWriter writer = resp.getWriter()) {

            if (req.getPathInfo() != null) {
                try {
                    int id = Integer.parseInt(req.getPathInfo().substring(1));
                    Student student = studentService.findById(id).orElseThrow(NullPointerException::new);
                    Printer.print(student, writer);
                } catch (NullPointerException ep) {
                    writer.println("Student not found");
                }
            } else {
                Printer.printStudent(studentService.findALl(), writer);
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = new Student();
        student.setName(req.getParameter("name"));
        studentService.add(student);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo());
            String name = req.getParameter("name");
            studentService.update(id, new Student());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            studentService.delete(id);
        }
    }
}
