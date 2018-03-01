package ua.nure.chub.Elective.db.dao;

import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;

import java.util.List;

/**
 * Author Lera
 * created 06.09.2017.
 */
public interface UserDAO {

    User getUserByLogin(String login) throws DAOException;

    User getUserById(int id) throws DAOException;

    void addUser(User user) throws DAOException;

    void updateUser(User user) throws DAOException;

    List<User> getUsers() throws DAOException;

    void setCourseForStudent(User student, Course course) throws DAOException;

    List<User> getUsersByRole(int role_id) throws DAOException;

    void deleteStudentFromCourse(User user, Course course) throws DAOException;

}
