package ua.nure.chub.Elective.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.StudentMark;
import ua.nure.chub.Elective.db.entity.Topic;
import ua.nure.chub.Elective.db.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author Lera
 * created 07.09.2017.
 */
public class MysqlCourseDAO implements CourseDAO {
    private static final Logger logger = Logger.getLogger(MysqlCourseDAO.class);

    private static MysqlCourseDAO mysqlCourseDAO;

    public static synchronized MysqlCourseDAO getInstance() {
        if (mysqlCourseDAO == null) {
            mysqlCourseDAO = new MysqlCourseDAO();
        }
        return mysqlCourseDAO;
    }

    @Override
    public Course getCourse(String courseName) throws DAOException {
        Connection con = null;
        Course course;
        try {
            con = MysqlDAOFactory.getConnection();
            course = getCourse(con, courseName);
        } catch (SQLException e) {
            logger.error("getCourse: Can't to get course. ", e);
            throw new DAOException("Can't to get course. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
        return course;
    }

    private Course getCourse(Connection con, String courseName) throws SQLException, DAOException {
        PreparedStatement st = null;
        Course course = null;
        try {
            st = con.prepareStatement(Queries.SQL_GET_COURSE);
            st.setString(1, courseName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                course = initCourse(rs);
            }
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        return course;
    }

    private Course initCourse(ResultSet rs) throws SQLException{
        Course course = new Course();
        course.setCourse_id(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setTopic(rs.getString("topic_name"));
        course.setTeacherName(rs.getString("teacher"));
        course.setDuration(rs.getInt("duration"));
        course.setStartDate(rs.getDate("start_date"));
        course.setEndDate(rs.getDate("end_date"));

        return course;
    }

    @Override
    public void addCourse(Course course) throws DAOException {
        Connection con = null;
        try {
            con = MysqlDAOFactory.getConnection();
            addCourse(con, course);
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("addCourse: Can't to add course. ", e);
            throw new DAOException("Can't to add course. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
    }

    public void addCourse(Connection con, Course course) throws SQLException {
        logger.trace("Start");
        PreparedStatement st = null;
        int id;
        try {
            st = con.prepareStatement(Queries.SQL_INSERT_COURSE, PreparedStatement.RETURN_GENERATED_KEYS);
            int columnIndex = 0;
            st.setString(++columnIndex, course.getName());
            st.setString(++columnIndex, course.getTopic());
            st.setString(++columnIndex, course.getFirstName());
            st.setString(++columnIndex, course.getLastName());
            st.setDouble(++columnIndex, course.getDuration());
            st.setDate(++columnIndex, course.getStartDate());
            st.setDate(++columnIndex, course.getEndDate());
            logger.debug("Query --> " + st);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                course.setCourse_id(id);
            } else {
                logger.error("Can't to add course");
                throw new SQLException("Can't to add course");
            }

        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        logger.trace("Finish");
    }

    @Override
    public void updateCourse(Course course) throws DAOException {
        Connection con = null;
        try {
            con = MysqlDAOFactory.getConnection();
            updateCourse(con, course);
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to update course ", e);
            throw new DAOException("Can't to update course " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
    }

    public void updateCourse(Connection con, Course course) throws SQLException {
        logger.trace("Start");
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(Queries.SQL_UPDATE_COURSE);
            int columnIndex = 0;
            st.setString(++columnIndex, course.getName());
            st.setString(++columnIndex, course.getTopic());
            st.setString(++columnIndex, course.getFirstName());
            st.setString(++columnIndex, course.getLastName());
            st.setDouble(++columnIndex, course.getDuration());
            st.setDate(++columnIndex, course.getStartDate());
            st.setDate(++columnIndex, course.getEndDate());
            st.setInt(++columnIndex, course.getCourse_id());
            logger.debug("Query --> " + st);
            st.executeUpdate();
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        logger.trace("Finish");

    }

    @Override
    public void deleteCourse(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = MysqlDAOFactory.getConnection();
            st = con.prepareStatement(Queries.SQL_DELETE_COURSE);
            st.setInt(1, id);
            st.executeUpdate();
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to delete course ", e);
            throw new DAOException("Can't to delete course " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    @Override
    public List<Course> getCourses() throws DAOException {
        Connection con = null;
        List<Course> courses = new LinkedList<>();
        Statement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(Queries.SQL_GET_ALL_COURSES);
            while (rs.next()) {
                courses.add(initCourse(rs));
            }
        } catch (SQLException e) {
            logger.error("Can't get all courses ", e);
            throw new DAOException("Can't get all courses " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courses;
    }

    @Override
    public Map<Course, Double> getPassedCourses(String login) throws DAOException {
        Connection con = null;
        Map<Course, Double> courseMark = new HashMap<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_PASSED_COURSES);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courseMark.put(getCourse(rs.getString("course_name")), rs.getDouble("mark"));
            }
        } catch (SQLException e) {
            logger.error("Can't get past courses  ", e);
            throw new DAOException("Can't get past courses " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courseMark;
    }

    @Override
    public List<Course> getFutureCourses() throws DAOException {
        Connection con = null;
        List<Course> courses = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_FUTURE_COURSES);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(getCourse(rs.getString("course_name")));
            }
        } catch (SQLException e) {
            logger.error("Can't get future courses  ", e);
            throw new DAOException("Can't get future courses " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courses;
    }

    public List<Course> getFutureCoursesOfStudent(String login) throws DAOException {
        Connection con = null;
        List<Course> courses = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_FUTURE_COURSES_OF_STUDENT);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(getCourse(rs.getString("course_name")));
            }
        } catch (SQLException e) {
            logger.error("Can't get future courses of student ", e);
            throw new DAOException("Can't get future courses of student" + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courses;
    }

    @Override
    public List<Course> getCurrentCourses(String login) throws DAOException {
        Connection con = null;
        List<Course> courses = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_CURRENT_COURSES);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(getCourse(rs.getString("course_name")));
            }
        } catch (SQLException e) {
            logger.error("Can't get current courses  ", e);
            throw new DAOException("Can't get current courses " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courses;
    }

    public List<Course> getTeacherCourses(User teacher) throws DAOException {
        Connection con = null;
        List<Course> courses = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_TEACHER_COURSES);
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(getCourse(rs.getString("course_name")));
            }
        } catch (SQLException e) {
            logger.error("Can't get teacher courses  ", e);
            throw new DAOException("Can't get teacher courses " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return courses;
    }

    public List<StudentMark> getJournal(Course course) throws DAOException {
        Connection con = null;
        List<StudentMark> journal = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_STUDENTS_ON_COURSE);
            statement.setString(1, course.getName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                journal.add(initStudentMark(rs));
            }
        } catch (SQLException e) {
            logger.error("Can't get journal for course  ", e);
            throw new DAOException("Can't get journal for course " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return journal;
    }

    @Override
    public void updateJournal(int id, Map<String, String[]> params) throws DAOException {
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            int studentId = Integer.parseInt(entry.getKey());
            double mark = Double.parseDouble(entry.getValue()[0]);
            updateMark(id, studentId, mark);
        }
    }

    private void updateMark(int courseId, int studentId, double mark) throws DAOException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_UPDATE_MARK);
            statement.setDouble(1, mark);
            statement.setInt(2, studentId);
            statement.setInt(3, courseId);
            statement.executeUpdate();
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to update mark ", e);
            throw new DAOException("Can't to update mark " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
    }

    private StudentMark initStudentMark(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");
        int courseId = rs.getInt("course_id");
        String firstNname = rs.getString("first_name");
        String lastNAme = rs.getString("last_name");
        double mark = rs.getDouble("mark");
        return new StudentMark(userId, courseId, firstNname, lastNAme, mark);
    }

    @Override
    public List<Topic> getTopics() throws DAOException {
        Connection con = null;
        List<Topic> topics = new LinkedList<>();
        Statement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(Queries.SQL_GET_TOPICS);
            while (rs.next()) {
                int id = rs.getInt("topic_id");
                String name = rs.getString("topic_name");
                topics.add(new Topic(id, name));
            }
        } catch (SQLException e) {
            logger.error("Can't get topics  ", e);
            throw new DAOException("Can't get topics " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return topics;
    }

    @Override
    public List<Course> getCoursesByTopic(String topic) throws DAOException {
        Connection con = null;
        List<String> courseNames = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_COURSES_BY_TOPIC);
            statement.setString(1, topic);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    courseNames.add(rs.getString("course_name"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can't get courses by topic  ", e);
            throw new DAOException("Can't get courses by topic" + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        List<Course> courses = new LinkedList<>();
        for (String courseName : courseNames) {
            courses.add(getCourse(courseName));
        }
        return courses;
    }

    @Override
    public Map<String, Double> getSuccsess(User teacher) throws DAOException {
        Connection con = null;
        Map<String, Double> succsess = new HashMap<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_SUCSESS);
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                succsess.put(rs.getString("course_name"), rs.getDouble("success"));
            }
        } catch (SQLException e) {
            logger.error("Can't get success  ", e);
            throw new DAOException("Can't get success" + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return succsess;
    }
}
