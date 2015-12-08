package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.dao.exception.DAOException;
import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.Voucher;

import java.sql.*;
import java.util.List;

public interface VoucherDAO extends GenericDao<Voucher> {
    @Override
    Voucher save(Voucher entity);

    @Override
    Voucher getByParameter(String param, String value);

    @Override
    Voucher getById(Long id);

    @Override
    List<Voucher> getAll();

    @Override
    boolean delete(Voucher entity);
}
