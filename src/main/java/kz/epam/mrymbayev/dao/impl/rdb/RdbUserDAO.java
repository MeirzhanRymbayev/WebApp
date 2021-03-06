package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.dao.exception.RdbUserDAOException;
import kz.epam.mrymbayev.dao.exception.RdbUserDAOGetByParameterException;
import kz.epam.mrymbayev.model.Role;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RdbUserDAO implements UserDAO {

    private Logger logger = Logger.getLogger(RdbUserDAO.class);

    private Connection connection;
    private PropertyManager propertyManager;

    public RdbUserDAO() {
    }

    public RdbUserDAO(Connection connection) {
        this.connection = connection;
        propertyManager = PropertyManager.getInstance();
        propertyManager.loadProperties("query.properties");
    }

    public User insert(User user) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("user.insert");

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            user.setId(id);
        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO insert() method");
            throw new RdbUserDAOException("Error with RdbUserDAO insert() method");
        }
        return user;
    }

    public User update(User user) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("user.update");
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setDouble(5, user.getDiscount());
            ps.setLong(6, user.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO update() method");
            throw new RdbUserDAOException("Error with RdbUserDAO insert() method");
        }
        return user;
    }

    @Override
    public User save(User user) {
        return user.isPersisted() ? update(user) : insert(user);
    }

    @Override
    public User getByParameter(String param, String value) throws RdbUserDAOGetByParameterException {
        String sql = propertyManager.getProperty("user.getByParameter");
        User user = new User();
        try {
            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery("SELECT * FROM USER WHERE " + param + " = '" + value + "';");
            rs.next();
            user.setId(rs.getLong("ID"));
            user.setAccountId(rs.getLong("ACCOUNT_ID"));
            user.setLogin(rs.getString("LOGIN"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setFirstName(rs.getString("FIRSTNAME"));
            user.setLastName(rs.getString("LASTNAME"));
            user.setRole(new Role(rs.getString("ROLE")));
        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO getByParameter() method", e);
            throw new RdbUserDAOGetByParameterException("Error with RdbUserDAO getByParameter() method", e);
        }
        return user;
    }

    @Override
    public User getById(long id) {
        String sql = propertyManager.getProperty("user.getById");
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getLong(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(new Role(rs.getString("ROLE")));
            user.setFirstName(rs.getString("FIRSTNAME"));
            user.setLastName(rs.getString("LASTNAME"));
            user.setDiscount(rs.getDouble("DISCOUNT"));
            user.setAccountId(rs.getLong("ACCOUNT_ID"));
        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO getById() method");
            throw new RdbUserDAOException("Error with RdbUserDAO getById() method");
        }
        return user;
    }

    @Override
    public List<User> getAllByLocale(int locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        final String sql = propertyManager.getProperty("user.getAll");
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            User user;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(new Role(rs.getString("ROLE")));
                user.setFirstName(rs.getString("FIRSTNAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setDiscount(rs.getFloat("DISCOUNT"));
                user.setAccountId(rs.getLong("ACCOUNT_ID"));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO getAll() method");
            throw new RdbUserDAOException("Error with RdbUserDAO getAll() method");
        }
        return list;
    }

    @Override
    public boolean delete(User user) {
        final String sql = propertyManager.getProperty("user.delete");
        boolean isDelete;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, user.getId());
            int affectedRows = ps.executeUpdate();
            isDelete = (affectedRows > 0) ? true : false;
        } catch (SQLException e) {
            logger.error("Error with RdbUserDAO delete() method");
            throw new RdbUserDAOException("Error with RdbUserDAO delete() method");
        }
        return isDelete;
    }
}
