package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Student;
import org.example.service.DefaultStudentService;
import org.example.service.StudentService;
import org.example.servlets.payload.UpdateStudent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = "/student/*")
public class StudentServlet extends HttpServlet {

    StudentService studentService = new DefaultStudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));

            try {
                Student student = studentService.findById(id);
                writer.write(student.toString());
            } catch (NullPointerException ep) {
                writer.write("Student not found");
            }
        } else {
            Set<Student> students = studentService.findAll();

            for (Student st : students) {
                writer.write(String.format("<b>id: %d Student:  %s </b> </br> ", st.getId(), st.getName()));
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setName(req.getParameter("name"));
        studentService.add(student);
        resp.sendRedirect("/api/student");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            studentService.deleteById(id);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo());
            String name = req.getParameter("name");

            studentService.update(new UpdateStudent(id, name));
        }
    }
}
