package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.Order;

import java.util.List;

public interface OrderDAO extends GenericDao<Order> {
    @Override
    Order save(Order entity);

    @Override
    Order getByParameter(String param, String value);

    @Override
    Order getById(long id);

    @Override
    List<Order> getAll();

    @Override
    List<Order> getAllByLocale(int locale);

    @Override
    boolean delete(Order entity);
}
