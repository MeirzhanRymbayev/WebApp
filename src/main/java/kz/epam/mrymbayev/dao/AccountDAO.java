package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.model.Account;

import java.util.List;

public interface AccountDAO extends GenericDao<Account>{
    @Override
    Account save(Account entity);

    @Override
    Account getByParameter(String param, String value);

    @Override
    Account getById(long id);

    @Override
    List<Account> getAll();

    @Override
    List<Account> getAllByLocale(int locale);

    @Override
    boolean delete(Account entity);
}
