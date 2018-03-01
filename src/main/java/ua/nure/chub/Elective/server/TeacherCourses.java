package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.TeacherService;

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
@WebServlet("/teacherCourses")
public class TeacherCourses extends HttpServlet {
    TeacherService ts = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = null;
        User teacher = (User) req.getSession().getAttribute("user");
        Map<String, Double> success = null;
        try {
            courses = ts.getCourses(teacher);
            success = ts.getSuccess(teacher);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        req.setAttribute("courses", courses);
        req.setAttribute("success", success);
        req.getRequestDispatcher("teacherCourses.jsp").forward(req,resp);
    }
}
