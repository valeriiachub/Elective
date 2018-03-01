package ua.nure.chub.Elective.db.dao.mysql;

/**
 * Author Lera
 * created 07.09.2017.
 */
public abstract class Queries {
    static final String SQL_GET_USER = "SELECT user_id,login,password, first_name," +
            "            last_name, role_id, blocked FROM users WHERE login = ? ";
    static final String SQL_GET_USER_BY_ID = "SELECT user_id,login,password, first_name," +
            "last_name, role_id, blocked FROM users WHERE user_id = ?";
    static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    static final String SQL_INSERT_USER = "INSERT INTO users (login, password, first_name, last_name, role_id, blocked)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    static final String SQL_UPDATE_USER = "UPDATE users "
            + "SET login = ?, password = ?, first_name = ?, last_name = ?,role_id = ?,"
            + "blocked = ? WHERE user_id = ?";
    static final String SQL_GET_COURSE = "SELECT course_id, course_name, topic_name, CONCAT(first_name, \" \" ,last_name) as teacher, duration, start_date, end_date" +
            " FROM elective.courses, elective.topics, users" +
            " WHERE courses.topic_id = topics.topic_id AND teacher = user_id AND course_name = ?";
    static final String SQL_INSERT_COURSE = "INSERT INTO courses(course_name, topic_id, teacher, duration, start_date, end_date) " +
            "    VALUES (?, (SELECT topic_id FROM topics WHERE topics.topic_name = ?), " +
            "(SELECT user_id FROM users WHERE users.first_name = ? AND users.last_name = ?)," +
            "            ?, ?, ?)";
    static final String SQL_UPDATE_COURSE = "UPDATE courses SET course_name = ?, " +
            "topic_id = (SELECT topic_id FROM topics WHERE topic_name = ?), " +
            "teacher = (SELECT user_id FROM users WHERE first_name = ? AND last_name = ?), duration = ?," +
            "start_date = ?, end_date = ? WHERE course_id = ?";
    static final String SQL_GET_ALL_COURSES = "SELECT * FROM courses";
    static final String SQL_GET_TOPICS = "SELECT topic_id, topic_name FROM topics";
    static final String SQL_GET_USER_BY_ROLE = "SELECT login FROM users WHERE role_id = ?";
    static final String SQL_DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?";
    static final String SQL_SET_COURSE_FOR_STUDENT = "INSERT INTO student_course(student_id, course_id) VALUES (?,?)";
    static final String SQL_GET_PASSED_COURSES = "SELECT course_name, mark FROM student_course, courses, users WHERE user_id = student_id AND " +
            "courses.course_id = student_course.course_id AND end_date < CURRENT_DATE() AND login = ?";
    static final String SQL_GET_FUTURE_COURSES = "SELECT course_name FROM courses WHERE start_date > CURRENT_DATE();";
    static final String SQL_GET_FUTURE_COURSES_OF_STUDENT = "SELECT course_name FROM student_course, courses, users WHERE users.user_id = student_course.student_id AND \n" +
            "courses.course_id = student_course.course_id AND start_date > CURRENT_DATE() AND login = ?";
    static final String SQL_GET_CURRENT_COURSES = "SELECT course_name FROM student_course, courses, users WHERE user_id = student_id AND " +
            "            courses.course_id = student_course.course_id AND start_date < CURRENT_DATE() " +
            "AND end_date > CURRENT_DATE() AND login = ?";
    static final String SQL_GET_STUDENTS_ON_COURSE = "SELECT user_id, student_course.course_id, first_name, last_name, mark FROM users, student_course, courses " +
            "WHERE users.user_id = student_course.student_id AND courses.course_id = student_course.course_id " +
            "AND course_name = ?";
    static final String SQL_GET_TEACHER_COURSES = "SELECT course_name FROM users, courses " +
            "WHERE courses.teacher = user_id AND first_name = ? AND last_name = ?";
    static final String SQL_GET_COURSES_BY_TOPIC = "SELECT course_name FROM courses, topics WHERE courses.topic_id = topics.topic_id" +
            " AND topic_name = ? ";
    static final String SQL_UPDATE_MARK = "UPDATE student_course SET mark = ? " +
            "WHERE student_id = ? AND course_id = ?";
    static final String SQL_DELETE_STUDENT_FROM_COURSE = "DELETE FROM student_course WHERE student_id = ? AND course_id = ?";
    static final String SQL_GET_SUCSESS = "SELECT course_name, COUNT(student_id) as students, (((SUM(mark)/(COUNT(mark)))*100)/5) as success " +
            "FROM courses, student_course, users " +
            "WHERE courses.course_id = student_course.course_id AND courses.teacher = user_id AND first_name = ? " +
            "AND last_name = ?" +
            "GROUP BY course_name";
}
