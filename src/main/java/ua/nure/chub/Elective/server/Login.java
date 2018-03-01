package ua.nure.chub.Elective.server;

import org.apache.log4j.Logger;
import ua.nure.chub.Elective.db.entity.Role;
import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.server.other.Hash;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author Lera
 * created 07.09.2017.
 */
@WebServlet("/role")
public class Login extends HttpServlet{
    private static final Logger logger = Logger.getLogger(Login.class);
    private UserDAO userDAO = null;
    private CourseDAO courseDAO = null;

    @Override
    public void init() {
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
        courseDAO = DAOFactory.getDAOFactory().getCourseDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String hash;
        User user;
        try {
            hash = Hash.encode(password);
            user = userDAO.getUserByLogin(login);
            req.login(login,hash);
        } catch (Exception e) {
            goBack(req,resp);
            return;
        }
        session.setAttribute("user", user);
        logger.debug("Set session attribute user --> " + user);
        if (user.getRole().equals(Role.ADMIN)) {
            logger.debug("Redirect to --> adminForm.jsp");
            resp.sendRedirect("adminForm.jsp");
        }
        if (user.getRole().equals(Role.STUDENT)) {
            logger.debug("Redirect to --> studentForm.jsp");
            resp.sendRedirect("studentForm.jsp");
        }
        if (user.getRole().equals(Role.TEACHER)) {
            logger.debug("Redirect to --> teacherForm.jsp");
            resp.sendRedirect("teacherForm.jsp");
        }
    }
    private void goBack(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logger.debug("Redirect --> login.jsp");
        response.sendRedirect("login.jsp");
    }
}
