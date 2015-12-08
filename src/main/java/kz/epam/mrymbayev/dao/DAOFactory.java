package kz.epam.mrymbayev.dao;

import org.apache.log4j.Logger;

public abstract class DAOFactory {
    private static DAOFactory instance;
    private static Logger logger = Logger.getLogger(DAOFactory.class);

    public static DAOFactory getInstance(){
        if(instance == null){
            logger.trace("Creating new JDBC DAO.");
            instance = new RdbDaoFactory(); // Pattern Data Access Object
        }
        return instance;
    }

    public abstract <T extends GenericDao> T getDao(Class<T> clazz);

    public abstract void beginTransaction();
    public abstract void commit();
    public abstract void rollback();

    public abstract void close();
}
