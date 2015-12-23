package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.AccountDAO;
import kz.epam.mrymbayev.dao.exception.RdbAccountDAOException;
import kz.epam.mrymbayev.dao.exception.RdbUserDAOException;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.Role;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RdbAccountDAO implements AccountDAO {

    private Connection connection;
    private PropertyManager propertyManager = PropertyManager.getInstance();
    private Logger logger = Logger.getLogger(RdbAccountDAO.class);

    @Override
    public Account save(Account entity) {
        return null;
    }

    @Override
    public Account getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Account getById(long id) {
        String sql = propertyManager.getProperty("account.getById");
        Account account = new Account();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            account.setId(rs.getLong(1));
            account.setUserId(rs.getLong(2));
            account.setAsset(rs.getInt(3));
        } catch (SQLException e) {
            logger.error("Error with RdbAccountDAO getById() method");
            throw new RdbAccountDAOException("Error with RdbAccountDAO getById() method");
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public List<Account> getAllByLocale(int locale) {
        return null;
    }

    @Override
    public boolean delete(Account entity) {
        return false;
    }
}
