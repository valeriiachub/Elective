package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.service.AdminService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author Lera
 * created 13.09.2017.
 */
@WebServlet("/block")
public class BlockUser extends HttpServlet {
    AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean blocked = Boolean.parseBoolean(req.getParameter("blocked"));
        String login = req.getParameter("login");
        User user;
        try {
            user = as.getStudent(login);
            if (blocked) {
                as.blockStudent(user);
            } else {
                as.unblockStudent(user);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/students");
    }
}
