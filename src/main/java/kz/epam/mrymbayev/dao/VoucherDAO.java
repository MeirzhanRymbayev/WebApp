package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.Voucher;

import java.util.List;

public interface VoucherDAO extends GenericDao<Voucher> {
    @Override
    Voucher save(Voucher entity);

    @Override
    Voucher getByParameter(String param, String value);

    @Override
    Voucher getById(long id);

    @Override
    List<Voucher> getAll();

    @Override
    boolean delete(Voucher entity);

    @Override
    List<Voucher> getAllByLocale(int locale);

    public Voucher insertI18nData(Voucher voucherI18nData);
}
