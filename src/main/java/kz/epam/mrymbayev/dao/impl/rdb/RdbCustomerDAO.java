package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.CustomerDAO;
import kz.epam.mrymbayev.dao.exception.RdbCustomerDAOException;
import kz.epam.mrymbayev.dao.exception.RdbDAOException;
import kz.epam.mrymbayev.model.Customer;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RdbCustomerDAO implements CustomerDAO {

    Connection connection;
    PropertyManager propertyManager = PropertyManager.getInstance();
    Logger logger = Logger.getLogger(RdbCustomerDAO.class);

    public RdbCustomerDAO(Connection connection) {
        this.connection = connection;
        propertyManager.loadProperties("query.properties");
    }

    public Customer insert(Customer customer){
        PreparedStatement ps;
        String sql = propertyManager.getProperty("customer.insert");

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getLogin());
            ps.setString(2, customer.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            customer.setId(id);
        } catch (SQLException e) {
            logger.error("Error with RdbCustomerDAO insert() method");
            throw new RdbDAOException("Error with RdbCustomerDAO insert() method");
        }
        return customer;
    }

    public Customer update(Customer customer){
        PreparedStatement ps;
        String sql = propertyManager.getProperty("customer.update");
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getLogin());
            ps.setString(2, customer.getPassword());
            ps.setLong(3, customer.getId());
        } catch (SQLException e) {
            logger.error("Error with RdbCustomerDAO update() method");
            throw new RdbCustomerDAOException("Error with RdbCustomerDAO insert() method");
        }
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customer.isPersisted() ? update(customer) : insert(customer);
    }

    @Override
    public Customer getByParameter(String param, String value) {
        String sql = propertyManager.getProperty("customer.getByParameter");
        Customer customer = new Customer();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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
        String sql = propertyManager.getProperty("customer.getById");
        Customer customer = new Customer();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
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
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = propertyManager.getProperty("customer.getAll");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Customer customer;
            while(resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getLong(1));
                customer.setLogin(resultSet.getString(2));
                customer.setPassword(resultSet.getString(3));
                //TODO check this condition
                if(resultSet.getString(4) != null){
                    customer.setVoucherID(resultSet.getLong(4));
                }
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(Customer customer) {
        final String sql = propertyManager.getProperty("customer.delete");
        boolean isDelete;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, customer.getId());
            int affectedRows = ps.executeUpdate();
            isDelete = (affectedRows > 0) ? true : false;
        } catch (SQLException e) {
            throw new Rd
        }
        return false;
    }
}
