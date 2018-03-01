package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.server.other.Hash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * Author Lera
 * created 09.09.2017.
 */
@WebServlet("/register")
public class Register extends HttpServlet {

    UserDAO dao = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao = DAOFactory.getDAOFactory().getUserDAO();
        User user = createUser(req);
        try {
            dao.addUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/role");
        rd.forward(req, resp);
    }

    private User createUser(HttpServletRequest request) {
        User user = null;
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String login = request.getParameter("login");
            String password = Hash.encode(request.getParameter("password"));
            user = new User(login, password, 2);
            user.setFirstName(firstName);
            user.setLastName(lastName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return user;
    }
}
