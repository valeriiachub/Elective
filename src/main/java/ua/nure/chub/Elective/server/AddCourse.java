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
import java.sql.Date;
import java.util.List;

/**
 * Author Lera
 * created 13.09.2017.
 */
@WebServlet("/addCourse")
public class AddCourse extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> teachers = null;
        try {
            teachers = as.getTeachers();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("addCourse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = createCourse(req);
        try {
            as.addCourse(course);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("addCourse.jsp");

    }

    private Course createCourse(HttpServletRequest req) {
        Course course = new Course();
        course.setName(req.getParameter("course_name"));
        course.setTopic(req.getParameter("topic"));
        course.setTeacherName(req.getParameter("teacher"));
        course.setDuration(Double.parseDouble(req.getParameter("duration")));
        course.setStartDate(Date.valueOf(req.getParameter("startDate")));
        course.setEndDate(Date.valueOf(req.getParameter("endDate")));

        return course;

    }
}
