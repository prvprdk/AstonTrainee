package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Direction;
import org.example.service.DefaultDirectionService;
import org.example.service.DirectionService;
import org.example.servlets.payload.DirectionUpdate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = "/direction/*")
public class DirectionServlet extends HttpServlet {
    DirectionService directionService = new DefaultDirectionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));

            try {
                Direction direction = directionService.findById(id);
                writer.write(direction.toString());
            } catch (NullPointerException ep) {
                writer.write("Direction not found");
            }
        } else {
            Set<Direction> directions = directionService.findAll();
            writer.write("""
                     <form method="post" action="/api/addDirection" >
                                    <input type="text" name="name"/>
                                    <input type="submit" value="add" />
                                   \s
                                    </form>
                    """);

            for (Direction dir : directions) {
                writer.write(String.format("<b>id: %d Name:  %s </b> </br> ", dir.getId(), dir.getName()));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Direction direction = new Direction();

        direction.setName(req.getParameter("name"));
        directionService.add(direction);
        resp.sendRedirect("/api/direction");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo());
            String name = req.getParameter("name");

            directionService.update(new DirectionUpdate(id, name));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            directionService.deleteById(id);
        }
    }

}
