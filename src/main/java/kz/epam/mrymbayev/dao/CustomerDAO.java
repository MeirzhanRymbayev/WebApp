package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.Customer;

import java.sql.*;

public class CustomerDAO {

    public static final String INSERT_NEW_CUSTOMER = "INSERT INTO CUSTOMER (ID, LOGIN, PASSWORD)" +
                                                     "VALUES (default, ?, ?)";
    public static final int FIRST_PARAM = 1;
    public static final int SECOND_PARAM = 2;
    ConnectionPool connectionPool;
    Connection connection;

    public Customer insert(Customer customer) throws DAOException {
        //TODO ConnectionPool
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_NEW_CUSTOMER);
            ps.setString(FIRST_PARAM, customer.getLogin());
            ps.setString(SECOND_PARAM, customer.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            customer.setId(id);
        } catch (SQLException e) {
            throw new DAOException();
        }
        return customer;
    }

    public Customer findByLogin(String login) {
        Customer customer = new Customer();
        long id;
        String customerLogin;
        String password;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE LOGIN = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            id = rs.getLong(1);
            customerLogin = rs.getString(2);
            password = rs.getString(3);

            customer.setId(id);
            customer.setLogin(customerLogin);
            customer.setPassword(password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
