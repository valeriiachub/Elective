package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Author Lera
 * created 18.09.2017.
 */
@WebServlet("/success")
public class GetSuccess extends HttpServlet {
    TeacherService ts = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        User teacher = (User) req.getSession().getAttribute("user");
        Map<String, Double> success = null;
        try {
            success = ts.getSuccess(teacher);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("success", success);
    }
}
