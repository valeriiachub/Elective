package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.Topic;
import ua.nure.chub.Elective.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author Lera
 * created 11.09.2017.
 */
@WebServlet("/topics")
public class GetTopics extends HttpServlet {
    private AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Topic> topics = null;
        try {
            topics = as.getAllTopics();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("topics", topics);
        RequestDispatcher rd = req.getRequestDispatcher("getTopics.jsp");
        rd.forward(req, resp);
    }
}
