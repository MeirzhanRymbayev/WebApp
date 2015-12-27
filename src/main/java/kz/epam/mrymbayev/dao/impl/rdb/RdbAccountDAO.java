package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.AccountDAO;
import kz.epam.mrymbayev.dao.exception.RdbAccountDAOException;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RdbAccountDAO implements AccountDAO {

    private Logger logger = Logger.getLogger(RdbAccountDAO.class);

    private Connection connection;
    private PropertyManager propertyManager;

    public RdbAccountDAO(Connection connection) {
        this.connection = connection;
        propertyManager = PropertyManager.getInstance();
        propertyManager.loadProperties("query.properties");
    }

    @Override
    public Account save(Account account) {
        return account.isPersisted() ? update(account) : insert(account);
    }

    private Account insert(Account account) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("account.insert");

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, account.getUserId());
            ps.setInt(2, account.getAsset());
            int rowCount = ps.executeUpdate();
            if (rowCount == 1) logger.trace("Account № " + account.getId() + " was successfully inserted.");
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            account.setId(id);
            ps.close();
        } catch (SQLException e) {
            logger.error("Error with RdbAccountDAO insert() method");
            throw new RdbAccountDAOException("Error with RdbAccountDAO insert() method");
        }
        return account;
    }

    private Account update(Account account) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("account.updateAsset");

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, account.getAsset());
            ps.setLong(2, account.getId());
            int rowCount = ps.executeUpdate();
            if (rowCount == 1) logger.trace("Account № " + account.getId() + " was successfully updated.");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error with RdbAccountDAO update() method");
            throw new RdbAccountDAOException("Error with RdbAccountDAO update() method");
        }
        return account;
    }

    @Override
    public Account getByParameter(String param, String value) {
        throw new UnsupportedOperationException("Not implemented yet.");
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
            account.setId(id);
            account.setAsset(rs.getInt("ASSET"));
            account.setUserId(rs.getLong("USER_ID"));
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error with RdbAccountDAO getById() method");
            throw new RdbAccountDAOException("Error with RdbAccountDAO getById() method");
        }
        return account;
    }


    @Override
    public List<Account> getAll() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<Account> getAllByLocale(int locale) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean delete(Account entity) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
