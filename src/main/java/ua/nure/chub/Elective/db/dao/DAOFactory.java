package ua.nure.chub.Elective.db.dao;

import ua.nure.chub.Elective.db.dao.mysql.MysqlDAOFactory;

/**
 * Author Lera
 * created 06.09.2017.
 */
public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();

    public abstract CourseDAO getCourseDAO();

    public static DAOFactory getDAOFactory() {
         return new MysqlDAOFactory();
    }

}
