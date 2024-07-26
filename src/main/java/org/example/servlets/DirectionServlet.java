package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Direction;
import org.example.service.DirectionService;
import org.example.service.imp.DirectionServiceImp;
import org.example.utils.Printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/direction/*")
public class DirectionServlet extends HttpServlet {
    DirectionService directionService = new DirectionServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (PrintWriter writer = resp.getWriter()) {
            if (req.getPathInfo() != null) {
                try {
                    int id = Integer.parseInt(req.getPathInfo().substring(1));
                    Direction direction = directionService.findById(id).orElseThrow(NullPointerException::new);
                    Printer.print(direction, writer);
                } catch (NullPointerException ep) {
                    writer.println("Direction not found");
                }
            } else {
                List<Direction> aLl = directionService.findALl();
                Printer.print(aLl, writer);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Direction direction = new Direction();
        direction.setNameDirection(req.getParameter("name"));
        directionService.add(direction);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo());
            String name = req.getParameter("name");
            directionService.update(id, new Direction());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            directionService.delete(id);
        }
    }
}
