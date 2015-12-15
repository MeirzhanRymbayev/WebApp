package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.BaseEntity;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class RdbDAOFactory<E extends BaseEntity> extends DAOFactory {

    public static final String DAO_IMPLS_PATH = "kz.epam.mrymbayev.dao.impl.rdb";
    private static final Logger log = Logger.getLogger(RdbDAOFactory.class);
    Connection connection;
    ConnectionPool instance;

    public RdbDAOFactory(){
        log.trace("Get connection from connection pool.");
        instance = ConnectionPool.getInstance();
        connection = instance.getConnection();
    }

    @SuppressWarnings("unchecked")//cast to T ensure
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        String DaoClassName = DAO_IMPLS_PATH + ".Rdb" + clazz.getSimpleName();
        GenericDao dao = null;
        try {
            log.trace("Trying to get DAO class object" + DaoClassName);
            Class daoClazz = Class.forName(DaoClassName);
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
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            ConnectionPool.ConnectionPooled connectionPooled = new ConnectionPool.ConnectionPooled(connection);
            connectionPooled.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
