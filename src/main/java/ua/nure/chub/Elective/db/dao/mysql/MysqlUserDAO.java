package ua.nure.chub.Elective.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.chub.Elective.db.dao.DAOException;
import ua.nure.chub.Elective.db.dao.UserDAO;
import ua.nure.chub.Elective.db.entity.Course;
import ua.nure.chub.Elective.db.entity.User;

/**
 * Author Lera
 * created 06.09.2017.
 */
public class MysqlUserDAO implements UserDAO {

    private static final Logger logger = Logger.getLogger(MysqlUserDAO.class);

    private static MysqlUserDAO mysqlUserDAO;

    public static synchronized MysqlUserDAO getInstance() {
        if (mysqlUserDAO == null) {
            mysqlUserDAO = new MysqlUserDAO();
        }
        return mysqlUserDAO;
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        Connection con = null;
        User user;
        try {
            con = MysqlDAOFactory.getConnection();
            user = getUser(con, login);
        } catch (SQLException e) {
            logger.error("getUser: Can't get user by login. ", e);
            throw new DAOException("Can't get user by login. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws DAOException {
        Connection con = null;
        User user;
        try {
            con = MysqlDAOFactory.getConnection();
            user = getUser(con, id);
        } catch (SQLException e) {
            logger.error("getUser: Can't get user by id. ", e);
            throw new DAOException("Can't get user by id. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
        return user;
    }

    @Override
    public void addUser(User user) throws DAOException {
        Connection con = null;
        try {
            con = MysqlDAOFactory.getConnection();
            addUser(con, user);
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("getUser: Can't to add user. ", e);
            throw new DAOException("Can't to add user. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        Connection con = null;
        try {
            con = MysqlDAOFactory.getConnection();
            con.setAutoCommit(false);
            updateUser(con, user);
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to update user. ", e);
            throw new DAOException("Can't to update user. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.close(con);
        }
    }

    @Override
    public List<User> getUsers() {
        Connection con = null;
        List<User> users = new LinkedList<>();
        Statement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(Queries.SQL_GET_ALL_USERS);
            while (rs.next()) {
                initUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return users;
    }

    @Override
    public void setCourseForStudent(User student, Course course) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = MysqlDAOFactory.getConnection();
            st = con.prepareStatement(Queries.SQL_SET_COURSE_FOR_STUDENT);
            st.setInt(1, student.getUser_id());
            st.setInt(2, course.getCourse_id());
            st.execute();
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to set course for user. ", e);
            throw new DAOException("Can't to set course for user. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    @Override
    public List<User> getUsersByRole(int role_id) throws DAOException {
        Connection con = null;
        List<User> users = new LinkedList<>();
        PreparedStatement statement = null;
        try {
            con = MysqlDAOFactory.getConnection();
            statement = con.prepareStatement(Queries.SQL_GET_USER_BY_ROLE);
            statement.setInt(1, role_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = getUserByLogin(rs.getString("login"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeStatement(statement);
            MysqlDAOFactory.close(con);
        }
        return users;
    }

    public void deleteStudentFromCourse(User user, Course course) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = MysqlDAOFactory.getConnection();
            st = con.prepareStatement(Queries.SQL_DELETE_STUDENT_FROM_COURSE);
            st.setInt(1, user.getUser_id());
            st.setInt(2, course.getCourse_id());
            st.executeUpdate();
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            MysqlDAOFactory.rollback(con);
            logger.error("Can't to delete student from course ", e);
            throw new DAOException("Can't to delete student from course " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    private User getUser(Connection con, int id) throws SQLException {
        PreparedStatement st = null;
        User user = null;
        try {
            st = con.prepareStatement(Queries.SQL_GET_USER_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = initUser(rs);
            }
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        return user;
    }

    private User getUser(Connection con, String login) throws SQLException {
        PreparedStatement st = null;
        User user = null;
        try {
            st = con.prepareStatement(Queries.SQL_GET_USER);
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = initUser(rs);
            }
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        return user;
    }

    private User initUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("login"), rs.getString("password"),
                rs.getInt("role_id"));
        user.setUser_id(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBlocked(rs.getBoolean("blocked"));
        return user;
    }

    public void addUser(Connection con, User user) throws SQLException {
        logger.trace("Start");
        PreparedStatement st = null;
        int id;
        try {
            st = con.prepareStatement(Queries.SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            int columnIndex = 0;
            st.setString(++columnIndex, user.getLogin());
            st.setString(++columnIndex, user.getPassword());
            st.setString(++columnIndex, user.getFirstName());
            st.setString(++columnIndex, user.getLastName());
            st.setInt(++columnIndex, user.getRoleId());
            st.setBoolean(++columnIndex, user.isBlocked());
            logger.debug("Query --> " + st);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                user.setUser_id(id);
            } else {
                logger.error("Can't to add user");
                throw new SQLException("Can't to add user");
            }
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        logger.trace("Finish");
    }

    public int updateUser(Connection con, User user) throws DAOException {
        logger.trace("Start");
        PreparedStatement st = null;
        int id = 0;
        try {
            st = con.prepareStatement(Queries.SQL_UPDATE_USER);
            int columnIndex = 0;
            st.setString(++columnIndex, user.getLogin());
            st.setString(++columnIndex, user.getPassword());
            st.setString(++columnIndex, user.getFirstName());
            st.setString(++columnIndex, user.getLastName());
            st.setInt(++columnIndex, user.getRoleId());
            st.setBoolean(++columnIndex, user.isBlocked());
            st.setInt(++columnIndex, user.getUser_id());
            logger.debug("Query --> " + st);
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't to update user. ", e);
            throw new DAOException("Can't to update user. " + e.getMessage(), e);
        } finally {
            MysqlDAOFactory.closeStatement(st);
        }
        logger.trace("Finish");
        return id;
    }
}
