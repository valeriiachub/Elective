package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
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
@WebServlet("/courses")
public class GetCourses extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topic = req.getParameter("topic");
        List<Course> courses = null;
        try {
            courses = as.getCoursesByTopic(topic);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("topic", topic);
        req.getSession().setAttribute("courses", courses);
        req.getRequestDispatcher("getCourses.jsp").forward(req, resp);

    }
}
