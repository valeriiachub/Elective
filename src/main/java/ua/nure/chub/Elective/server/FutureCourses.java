package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Lera
 * created 15.09.2017.
 */
@WebServlet("/futureCourses")
public class FutureCourses extends HttpServlet {
    StudentService ss = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        List<Course> allFutureCourses;
        List<Course> studentFutureCourses;
        Map<Course, Boolean> registered = null;
        try {
            allFutureCourses = ss.getFutureCourses();
            studentFutureCourses = ss.getFutureCoursesOfStudent(user.getLogin());
            registered = registeredCourses(allFutureCourses, studentFutureCourses);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("registered", registered);

        req.getRequestDispatcher("futureCourses.jsp").forward(req, resp);
    }

    private Map<Course, Boolean> registeredCourses(List<Course> allFutureCourses, List<Course> studentFutureCourses) {
        Map<Course, Boolean> registered = new HashMap<>();
        for (Course course : studentFutureCourses) {
            if (allFutureCourses.contains(course)) {
                registered.put(course, true);
                allFutureCourses.remove(course);
            }
        }
        for (Course futureCourse : allFutureCourses) {
            registered.put(futureCourse, false);
        }
        return registered;
    }
}
