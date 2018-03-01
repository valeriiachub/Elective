package ua.nure.chub.Elective.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.chub.Elective.db.dao.CourseDAO;
import ua.nure.chub.Elective.db.dao.DAOFactory;
import ua.nure.chub.Elective.db.dao.UserDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author Lera
 * created 06.09.2017.
 */
public class MysqlDAOFactory extends DAOFactory {
    private static final Logger logger = Logger.getLogger(MysqlDAOFactory.class);

    public MysqlDAOFactory() {

    }
    static Connection getConnection() throws SQLException {
        logger.trace("Start");
        Connection con;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
            con = ds.getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
        } catch (NamingException ex) {
            logger.error("Cannot obtain a connection from the pool", ex);
            throw new SQLException("Cannot obtain a connection from the pool", ex);
        }
        logger.trace("Finish");
        return con;
    }

    protected static void rollback(Connection con) {
        if (con != null) {
            try {
                logger.debug("Try rollback.");
                con.rollback();
            } catch (SQLException e) {
                logger.error("Can not rollback transaction.", e);
            }
        }
    }

    protected static void commit(Connection con) {
        try {
            logger.debug("Try commit transaction");
            con.commit();
        } catch (SQLException e) {
            logger.error("Can not commit transaction." + e);
            try {
                logger.debug("Try rollback transaction.");
                con.rollback();
            } catch (SQLException e1) {
                logger.error("Can not rollback transaction." + e1);
            }
        }
    }

    protected static void close(Connection con) {
        try {
            logger.debug("Try close connection.");
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            logger.error("Can not close connection." + e.getMessage());
        }
    }

    static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                logger.debug("Try close statement");
                stmt.close();
            } catch (SQLException e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return MysqlUserDAO.getInstance();
    }

    @Override
    public CourseDAO getCourseDAO() {
        return MysqlCourseDAO.getInstance();
    }
}
