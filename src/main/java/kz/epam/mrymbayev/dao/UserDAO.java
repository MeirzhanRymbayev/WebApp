package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.User;

import java.util.List;

public interface UserDAO extends GenericDao<User> {
    @Override
    User save(User entity);

    @Override
    User getByParameter(String param, String value);

    @Override
    User getById(long id);

    @Override
    List<User> getAll();

    @Override
    boolean delete(User entity);
}
