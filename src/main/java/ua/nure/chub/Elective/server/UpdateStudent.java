package ua.nure.chub.Elective.server;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.User;
import ua.nure.chub.Elective.server.other.Hash;
import ua.nure.chub.Elective.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Author Lera
 * created 15.09.2017.
 */
@WebServlet("/updateStudent")
public class UpdateStudent extends HttpServlet {
    StudentService ss = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user;
        try {
            user = ss.getStudent(login);
            req.setAttribute("user", user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user;
        try {
            user = createUser(req);
            ss.updateProfile(user);
        } catch (DAOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private User createUser(HttpServletRequest req) throws NoSuchAlgorithmException {
        int id = Integer.parseInt(req.getParameter("userId"));
        String login = req.getParameter("login");
        String password = Hash.encode(req.getParameter("password"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        boolean blocked = Boolean.parseBoolean(req.getParameter("blocked"));
        User user = new User(login, password, 2);
        user.setUser_id(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
