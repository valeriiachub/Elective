package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author Lera
 * created 12.09.2017.
 */
@WebServlet("/students")
public class GetStudents extends HttpServlet {

    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> students;
        try {
            students = as.getStudents();
            req.getSession().setAttribute("students", students);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("getStudents.jsp").forward(req, resp);
    }
}
