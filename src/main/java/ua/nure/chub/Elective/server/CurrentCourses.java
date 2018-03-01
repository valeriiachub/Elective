package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author Lera
 * created 15.09.2017.
 */
@WebServlet("/currentCourses")
public class CurrentCourses extends HttpServlet {
    StudentService ss = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();
        List<Course> currentCourses = null;
        try {
            currentCourses = ss.getCurrentCourses(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("currentCourses", currentCourses);
        req.getRequestDispatcher("currentCourses.jsp").forward(req, resp);
    }
}
