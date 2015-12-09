package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.CustomerDAO;
import kz.epam.mrymbayev.dao.exception.RdbDAOException;
import kz.epam.mrymbayev.model.Customer;
import kz.epam.mrymbayev.pm.PropertyManager;

import java.sql.*;
import java.util.List;

public class RdbCustomerDAO implements CustomerDAO {

    Connection connection;
    PropertyManager propertyManager = PropertyManager.getInstance();

    public RdbCustomerDAO(Connection connection) {
        this.connection = connection;
        propertyManager.loadProperties("query.properties");
    }

    public Customer insert(Customer customer){
        PreparedStatement ps;
        String query = propertyManager.getProperty("customer.insert");

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getLogin());
            ps.setString(2, customer.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            customer.setId(id);
        } catch (SQLException e) {
            throw new RdbDAOException();
        }
        return customer;
    }

    public Customer update(Customer entity){
        PreparedStatement ps;
        String query = propertyManager.getProperty("customer.update");
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setLong(3, entity.getId()); //example: UPDATE CUSTOMER SET LOGIN='Guitarist3', PASSWORD='123' WHERE ID = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
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

    @Override
    public Customer save(Customer entity) {
        return entity.isPersisted() ? update(entity) : insert(entity);
    }

    @Override
    public Customer getByParameter(String param, String value) {
        String query = propertyManager.getProperty("customer.getByParameter");
        Customer customer = new Customer();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, param);
            ps.setString(2, value);
            ResultSet rs = ps.executeQuery();

            customer.setId(rs.getLong(1));
            customer.setLogin(rs.getString(2));
            customer.setPassword(rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public boolean delete(Customer entity) {
        return false;
    }
}
