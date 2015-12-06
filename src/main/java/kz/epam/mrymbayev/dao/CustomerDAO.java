package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public static final String INSERT_NEW_CUSTOMER = "INSERT INTO CUSTOMER (ID, LOGIN, PASSWORD)" +
                                                     "VALUES (default, ?, ?)";
    public static final int FIRST_PARAM = 1;
    public static final int SECOND_PARAM = 2;

    public Customer insert(Customer customer) throws DAOException {
        //TODO ConnectionPool
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
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
}
