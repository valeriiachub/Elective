package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.server.other.Hash;
import ua.nure.chub.Elective.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Author Lera
 * created 10.09.2017.
 */
@WebServlet("/addTeacher")
public class AddTeacher extends HttpServlet {

    private AdminService as = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addTeacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            as.addTeacher(createUser(req));
            resp.sendRedirect("addTeacher.jsp");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private User createUser(HttpServletRequest request) {
        User user = null;
        try {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String login = request.getParameter("login");
            String password = Hash.encode(request.getParameter("password"));
            user = new User(login, password, 3);
            user.setFirstName(firstName);
            user.setLastName(lastName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return user;
    }
}
