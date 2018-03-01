package ua.nure.chub.Elective.service;

import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.db.dao.mysql.MysqlCourseDAO;
import ua.nure.chub.Elective.db.dao.mysql.MysqlDAOFactory;
import ua.nure.chub.Elective.db.dao.mysql.MysqlUserDAO;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.Topic;
import ua.nure.chub.Elective.db.entity.User;

import java.util.List;

/**
 * Author Lera
 * created 08.09.2017.
 */
public class AdminService {
    private UserDAO userDAO;
    private CourseDAO courseDAO;

    public AdminService() {
        userDAO = MysqlUserDAO.getInstance();
        courseDAO = MysqlCourseDAO.getInstance();
    }

    public void addTeacher(User user) throws DAOException {
        userDAO.addUser(user);
    }

    public User getStudent(String login) throws DAOException {
        return userDAO.getUserByLogin(login);
    }

    public Course getCourse(String name) throws DAOException {
        return courseDAO.getCourse(name);
    }

    public void setTeacherToCourse(String teacher, Course course) throws DAOException {
        course.setTeacherName(teacher);
        courseDAO.updateCourse(course);
    }

    public void addCourse(Course course) throws DAOException {
        courseDAO.addCourse(course);
    }

    public void deleteCourse(int id) throws DAOException {
        courseDAO.deleteCourse(id);
    }

    public void updateCourse(Course course) throws DAOException {
        courseDAO.updateCourse(course);
    }

    public void blockStudent(User student) throws DAOException {
        student.setBlocked(true);
        userDAO.updateUser(student);
    }

    public void unblockStudent(User student) throws DAOException {
        student.setBlocked(false);
        userDAO.updateUser(student);
    }

    public List<Topic> getAllTopics() throws DAOException {
        return courseDAO.getTopics();
    }

    public List<Course> getCoursesByTopic(String topic) throws DAOException {
        return courseDAO.getCoursesByTopic(topic);
    }

    public List<User> getStudents() throws DAOException {
        return userDAO.getUsersByRole(2);
    }

    public List<User> getTeachers() throws DAOException {
        return userDAO.getUsersByRole(3);
    }
}
