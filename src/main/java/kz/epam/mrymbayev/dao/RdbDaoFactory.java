package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.dbpool.ConnectionPool;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class RdbDAOFactory extends DAOFactory {

    public static final String DAO_IMPLS_PATH = "kz.epam.mrymbayev.dao.impl.rdb";
    private static final Logger log = Logger.getLogger("kz.epam");
    Connection connection;
    ConnectionPool pool;

    public RdbDAOFactory() {
        log.trace("Get connection from connection pool.");
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
    }

    @SuppressWarnings("unchecked")//cast to T ensure
    @Override
    public <T extends GenericDao> T getDao(Class<T> clazz) {
        String DaoClassName = DAO_IMPLS_PATH + ".Rdb" + clazz.getSimpleName();
        T dao;
        try {
            log.trace("Trying to get DAO class object" + DaoClassName);
            Class<T> daoClazz = (Class<T>) Class.forName(DaoClassName);
            dao = daoClazz.getDeclaredConstructor(Connection.class).newInstance(connection);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error("Unavailable create DAO instance", e);
            throw new RuntimeException("Unavailable to create DAO instance", e);
        }
        log.trace("DAO instance was created");
        return dao;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            log.trace("Transaction was started.");
        } catch (SQLException e) {
            log.error("Transaction start error.", e);
        }

    }

    @Override
    public void commit() {
        try {
            connection.commit();
            log.trace("Transaction was finished.");
        } catch (SQLException e) {
            log.error("Transaction finish error.", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            log.trace("Transaction rollback.");
        } catch (SQLException e) {
            log.error("Transaction rollback error.", e);
        }
    }

    @Override
    public void close() {
        try {
            ConnectionPool.ConnectionPooled connectionPooled = new ConnectionPool.ConnectionPooled(connection);
            connectionPooled.close();
            log.trace("Connection was returned to connection pool.");
        } catch (SQLException e) {
            log.error("Connection return to pool operation error.", e);
        }
    }
}
