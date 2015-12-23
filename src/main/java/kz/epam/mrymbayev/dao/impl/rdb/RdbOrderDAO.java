package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.OrderDAO;
import kz.epam.mrymbayev.dao.exception.RdbOrderDAOException;
import kz.epam.mrymbayev.dao.exception.RdbUserDAOException;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RdbOrderDAO implements OrderDAO {

    private Connection connection;
    private PropertyManager propertyManager = PropertyManager.getInstance();
    private Logger logger = Logger.getLogger(RdbOrderDAO.class);

    @Override
    public Order save(Order order) {
        return order.isPersisted() ? update(order) : insert(order);
    }

    public Order insert(Order order) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("order.insert");

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, order.getVoucherId());
            ps.setLong(2, order.getUserId());
            ps.setInt(3, order.getCost());
            ps.setInt(4, order.getDiscount());
            ps.setDate(5, order.getDate());
            ps.setInt(6, order.getAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            order.setId(id);
        } catch (SQLException e) {
            logger.error("Error with RdbOrderDAO insert() method");
            throw new RdbOrderDAOException("Error with RdbOrderDAO insert() method");
        }
        return order;
    }

    public Order update(Order order) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("order.update");
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, order.getVoucherId());
            ps.setLong(2, order.getUserId());
            ps.setInt(3, order.getCost());
            ps.setInt(4, order.getDiscount());
            ps.setDate(5, order.getDate());
            ps.setInt(6, order.getAmount());
            ps.setLong(7, order.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error with RdbOrderDAO update() method");
            throw new RdbOrderDAOException("Error with RdbOrderDAO insert() method");
        }
        return order;
    }

    @Override
    public Order getByParameter(String param, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Order getById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAllByLocale(int locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Order entity) {
        throw new UnsupportedOperationException();
    }
}