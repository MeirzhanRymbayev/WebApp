package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.Customer;

import java.util.List;

public interface CustomerDAO extends GenericDao<Customer> {
    @Override
    Customer save(Customer entity);

    @Override
    Customer getByParameter(String param, String value);

    @Override
    Customer getById(Long id);

    @Override
    List<Customer> getAll();

    @Override
    boolean delete(Customer entity);
}
