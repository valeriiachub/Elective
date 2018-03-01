package ua.nure.chub.Elective.service;

import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.db.dao.mysql.MysqlCourseDAO;
import ua.nure.chub.Elective.db.dao.mysql.MysqlUserDAO;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author Lera
 * created 08.09.2017.
 */
public class StudentService {
    private UserDAO userDAO;
    private CourseDAO courseDAO;

    public StudentService() {
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
        courseDAO = DAOFactory.getDAOFactory().getCourseDAO();
    }

    public void registerOnCourse(String login, String courseName) throws DAOException {
        User student = userDAO.getUserByLogin(login);
        Course course = courseDAO.getCourse(courseName);
        userDAO.setCourseForStudent(student, course);
    }

    public void unregisterFromCourse(String login, String courseName) throws DAOException {
        User student = userDAO.getUserByLogin(login);
        Course course = courseDAO.getCourse(courseName);
        userDAO.deleteStudentFromCourse(student, course);
    }

    public User getStudent(String login) throws DAOException {
        return userDAO.getUserByLogin(login);
    }

    public void updateProfile(User user) throws DAOException {
        userDAO.updateUser(user);
    }

    public Map<Course, Double> getPassedCourses(String login) throws DAOException {
        return courseDAO.getPassedCourses(login);
    }

    public List<Course> getFutureCourses() throws DAOException {
        return courseDAO.getFutureCourses();
    }

    public List<Course> getFutureCoursesOfStudent(String login) throws DAOException {
        return courseDAO.getFutureCoursesOfStudent(login);
    }

    public List<Course> getCurrentCourses(String login) throws DAOException {
        return courseDAO.getCurrentCourses(login);
    }
}


