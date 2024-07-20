package org.example.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.DefaultLearnClassService;
import org.example.service.LearnClassService;

import java.io.IOException;

@WebServlet(urlPatterns = "/addStudentToClass")
public class AddStudentToClassServlet extends HttpServlet {

    LearnClassService classService = new DefaultLearnClassService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idLearnClass = Integer.parseInt(req.getParameter("idClass"));
        int idStudent = Integer.parseInt(req.getParameter("idClass"));

        classService.addStudentToClass(idLearnClass, idStudent);
        resp.sendRedirect("/api/direction");


    }
}
