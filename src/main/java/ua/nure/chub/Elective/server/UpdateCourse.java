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
@WebServlet("/updateCourse")
public class UpdateCourse extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("name");
        Course course;
        List<User> teachers = null;
        try {
            teachers = as.getTeachers();
            course = as.getCourse(courseName);
            req.setAttribute("course", course);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("updateCourse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = createCourse(req);
        try {
            as.updateCourse(course);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("updateCourse.jsp");

    }

    private Course createCourse(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("course_id"));
        String name = req.getParameter("course_name");
        String topic = req.getParameter("topic");
        String teacher = req.getParameter("teacher");
        double duration = Double.parseDouble(req.getParameter("duration"));
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));

        Course course = new Course();
        course.setCourse_id(id);
        course.setName(name);
        course.setTopic(topic);
        course.setTeacherName(teacher);
        course.setDuration(duration);
        course.setStartDate(startDate);
        course.setEndDate(endDate);

        return course;

    }
}
