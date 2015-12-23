package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.BaseEntity;

import java.util.List;

public interface GenericDao<T extends BaseEntity> {
    //CREATE and UPDATE action
    T save(T entity);

    //READ
    T getByParameter(String param, String value);
    T getById(long id);
    List<T> getAll();
    //Check to necessary this method in all realizations
    List<T> getAllByLocale(int locale);

    //DELETE
    boolean delete(T entity);



}
