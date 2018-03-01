package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Lera
 * created 14.09.2017.
 */
@WebServlet("/updateJournal")
public class UpdateJournal extends HttpServlet {
    TeacherService ts = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getHeader("Referer")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, String[]> parameterMap = new HashMap<>(req.getParameterMap());
        parameterMap.remove("id");
        try {
            ts.updateJournal(id, parameterMap);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
