package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.BaseEntity;

import java.util.List;

public interface GenericDao<T extends BaseEntity> {
    //CREATE and UPDATE actions
    T save(T entity);

    //READ
    T getByParameter(String param, String value);
    T getById(Long id);
    List<T> getAll();

    //DELETE
    boolean delete(T entity);



}
