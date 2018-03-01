package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.StudentMark;
import ua.nure.chub.Elective.service.TeacherService;

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
@WebServlet("/journal")
public class CourseJournal extends HttpServlet {
    TeacherService ts = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));
        List<StudentMark> journal = null;
        try {
            journal = ts.getJournal(courseName);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("journal", journal);
        req.setAttribute("name", courseName);
        req.setAttribute("id" , id);
        req.getRequestDispatcher("courseJournal.jsp").forward(req, resp);
    }
}
