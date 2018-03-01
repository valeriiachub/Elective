package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
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
 * created 14.09.2017.
 */
@WebServlet("/setTeacher")
public class SetTeacher extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> teachers = null;
        String name = req.getParameter("name");
        try {
            teachers = as.getTeachers();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("name", name);
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("setTeacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String teacherName = req.getParameter("teacher");
        Course course;
        try {
            course = as.getCourse(req.getParameter("name"));
            as.setTeacherToCourse(teacherName, course);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("getCourses.jsp");
    }
}
