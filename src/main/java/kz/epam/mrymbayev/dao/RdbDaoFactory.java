package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.BaseEntity;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class RdbDAOFactory extends DAOFactory {

    public static final String DAO_IMPLS_PATH = "kz.epam.mrymbayev.dao.impl.rdb";
    private static final Logger log = Logger.getLogger(RdbDAOFactory.class);
    Connection connection;
    ConnectionPool pool;

    public RdbDAOFactory(){
        log.trace("Get connection from connection pool.");
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection(); //ты коннекшн из пула когда берешь?
        // при создании фабрики
        // а сколько раз за всю работу прилаги создаешь фабрику? 1
        // итого сколько коннекшнов ты возьмешь из пула?
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
            //если ты кидаешь вверх, то там где поймаешь тоже будешь логгировать тут может быть стремное дублирование логов
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
            e.printStackTrace();
        }

    }

    @Override
    public void commit() {
        try {
            connection.commit();
            log.trace("Transaction was finished.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            log.trace("Transaction rollback.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            ConnectionPool.ConnectionPooled connectionPooled = new ConnectionPool.ConnectionPooled(connection);
            connectionPooled.close();
            log.trace("Connection was returned to connection pool.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
