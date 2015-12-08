package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.BaseEntity;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

public class RdbDaoFactory<E extends BaseEntity> extends DAOFactory {

    public static final String DAO_IMPLS_PATH = "kz.epam.mrymbayev.dao.impl";
    private static final Logger log = Logger.getLogger(RdbDaoFactory.class);
    Connection connection;

    public RdbDaoFactory(){
        log.trace("Get connection from connection pool.");
        ConnectionPool instance = ConnectionPool.getInstance();
        connection = instance.getConnection();
    }

    //@SuppressWarnings("unchecked")
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        String DaoClassName = DAO_IMPLS_PATH + ".Jdbc" + clazz.getSimpleName();
        GenericDao dao = null;
        try {
            log.trace("Trying to get DAO class object" + DaoClassName);
            Class<?> daoClazz = Class.forName(DaoClassName);
            dao = (T) daoClazz.getDeclaredConstructor(Connection.class).newInstance(connection);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error("Unavailable create DAO instance");
            throw new RuntimeException("Unavailable to create DAO instance", e);
        }
        log.trace("DAO instance was created");
        return (T) dao;
    }

    @Override
    public void beginTransaction() {


    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }
}
