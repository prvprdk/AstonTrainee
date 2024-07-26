package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Audience;
import org.example.entity.Direction;
import org.example.service.AudienceService;
import org.example.service.imp.AudienceServiceImp;
import org.example.utils.Printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/audience/*")
public class AudienceServlet extends HttpServlet {
    AudienceService audienceService = new AudienceServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (PrintWriter writer = resp.getWriter()) {

            if (req.getPathInfo() != null) {
                try {
                    int id = Integer.parseInt(req.getPathInfo().substring(1));
                    Audience audience = audienceService.findById(id).orElseThrow(NullPointerException::new);
                    Printer.print(audience, writer);
                } catch (NullPointerException ep) {
                    writer.println("Direction not found");
                }

            } else {
                List<Audience> aLl = audienceService.findAll();
                Printer.printAudi(aLl, writer);
            }
        }
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Audience audience = new Audience();
    audience.setName(req.getParameter("name"));
    audienceService.add(audience);
}

@Override
protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if (req.getPathInfo() != null) {
        int id = Integer.parseInt(req.getPathInfo());
        String name = req.getParameter("name");
        audienceService.update(id, new Audience());
    }
}

@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getPathInfo() != null) {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        audienceService.delete(id);
    }
}
}
