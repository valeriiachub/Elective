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
import java.util.Map;

/**
 * Author Lera
 * created 14.09.2017.
 */
@WebServlet("/passedCourses")
public class PassedCourses extends HttpServlet {
    StudentService ss = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Map<Course, Double> passedCourses = null;
        try {
            passedCourses = ss.getPassedCourses(user.getLogin());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("passedCourses", passedCourses);
        req.getRequestDispatcher("passedCourses.jsp").forward(req, resp);
    }
}
