package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Direction;
import org.example.entity.LearnClass;
import org.example.service.DefaultLearnClassService;
import org.example.service.LearnClassService;
import org.example.servlets.payload.LearnClassUpdate;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/class/*")
public class LearnClassServlet extends HttpServlet {

    LearnClassService classService = new DefaultLearnClassService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));

            try {
                LearnClass learnClass = classService.findById(id);
                writer.write(learnClass.toString());
            } catch (NullPointerException ep) {
                writer.write("Class not found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LearnClass learnClass = new LearnClass();
        Direction direction = new Direction();

        int idDirection = Integer.parseInt(req.getParameter("id_direction"));
        direction.setId(idDirection);
        learnClass.setName(req.getParameter("name"));
        learnClass.setDirection(direction);

        classService.add(learnClass);
        resp.sendRedirect("/api/direction/" + idDirection);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getPathInfo().substring(1));
        classService.deleteById(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo());
            String name = req.getParameter("name");

            classService.update(new LearnClassUpdate(id, name));
        }
    }
}
