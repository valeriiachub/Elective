package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author Lera
 * created 13.09.2017.
 */
@WebServlet("/deleteCourse")
public class DeleteCourse extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            as.deleteCourse(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
