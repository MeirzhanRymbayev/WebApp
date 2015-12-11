package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.TourAgent;

import java.util.List;

public interface TourAgentDAO extends GenericDao<TourAgent> {
    @Override
    TourAgent save(TourAgent entity);

    @Override
    TourAgent getByParameter(String param, String value);

    @Override
    TourAgent getById(Long id);

    @Override
    List<TourAgent> getAll();

    @Override
    boolean delete(TourAgent entity);
}
