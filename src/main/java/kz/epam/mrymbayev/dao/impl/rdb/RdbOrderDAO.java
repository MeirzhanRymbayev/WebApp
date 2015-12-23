package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.OrderDAO;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class RdbOrderDAO implements OrderDAO {

    private Connection connection;
    private PropertyManager propertyManager = PropertyManager.getInstance();
    private Logger logger = Logger.getLogger(RdbOrderDAO.class);

    @Override
    public Order save(Order entity) {
        return null;
    }

    @Override
    public Order getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getAllByLocale(int locale) {
        return null;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }
}
