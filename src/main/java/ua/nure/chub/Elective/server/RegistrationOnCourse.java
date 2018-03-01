package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author Lera
 * created 15.09.2017.
 */
@WebServlet("/regOnCourse")
public class RegistrationOnCourse extends HttpServlet {
    private static final long serialVersionUID = 6031488570133118057L;
    StudentService ss = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean reg = Boolean.parseBoolean(req.getParameter("reg"));
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String courseName = req.getParameter("courseName");
        try {
            if (reg) {
                ss.registerOnCourse(user.getLogin(), courseName);
            } else {
                ss.unregisterFromCourse(user.getLogin(), courseName);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/futureCourses");
    }
}
