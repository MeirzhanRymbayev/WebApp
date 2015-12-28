package kz.epam.mrymbayev.dao;

import org.apache.log4j.Logger;

public abstract class DAOFactory {
    private static Logger logger = Logger.getLogger("kz.epam");

    public static DAOFactory newInstance() {
        DAOFactory daoFactory = new RdbDAOFactory();
        logger.trace("Creating new JDBC DAO.");
        return daoFactory;
    }

    public abstract <T extends GenericDao> T getDao(Class<T> clazz);

    public abstract void beginTransaction();

    public abstract void commit();

    public abstract void rollback();

    public abstract void close();
}
