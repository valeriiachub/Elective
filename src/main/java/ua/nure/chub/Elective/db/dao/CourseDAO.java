package ua.nure.chub.Elective.db.dao;

import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.StudentMark;
import ua.nure.chub.Elective.db.entity.Topic;
import ua.nure.chub.Elective.db.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Author Lera
 * created 07.09.2017.
 */
public interface CourseDAO {
    Course getCourse(String login) throws DAOException;

    void addCourse(Course course) throws DAOException;

    void updateCourse(Course course) throws DAOException;

    void deleteCourse(int id) throws DAOException;

    List<Course> getCourses() throws DAOException;

    Map<Course, Double> getPassedCourses(String login) throws DAOException;

    List<Course> getFutureCourses() throws DAOException;

    List<Course> getFutureCoursesOfStudent(String login) throws DAOException;

    List<Course> getCurrentCourses(String login) throws DAOException;

    List<Course> getTeacherCourses(User teacher) throws DAOException;

    List<StudentMark> getJournal(Course course) throws DAOException;

    List<Topic> getTopics() throws DAOException;

    List<Course> getCoursesByTopic(String topic) throws DAOException;

    void updateJournal(int id, Map<String, String[]> params) throws DAOException;

    Map<String, Double> getSuccsess(User teacher) throws DAOException;
}
