package ua.nure.chub.Elective.service;

import java.util.List;
import java.util.Map;

import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.StudentMark;
import ua.nure.chub.Elective.db.entity.User;

/**
 * Author Lera
 * created 08.09.2017.
 */
public class TeacherService {

    private CourseDAO courseDAO;
    private UserDAO userDAO;

    public TeacherService() {
        courseDAO = DAOFactory.getDAOFactory().getCourseDAO();
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
    }

    public List<Course> getCourses(User user) throws DAOException {
        List<Course> courses = courseDAO.getTeacherCourses(user);
        return courses;
    }

    public List<StudentMark> getJournal(String courseName) throws DAOException {
        Course course = courseDAO.getCourse(courseName);
        return courseDAO.getJournal(course);
    }

    public User getTeacher(int id) throws DAOException {
        return userDAO.getUserById(id);
    }

    public void updateJournal(int id, Map<String, String[]> params) throws DAOException {
        courseDAO.updateJournal(id, params);
    }

    public Map<String, Double> getSuccess(User teacher) throws DAOException {
        return courseDAO.getSuccsess(teacher);
    }
}
