package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.AccountDAO;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;

public class VoucherService {
    public boolean orderVoucher(Long voucherId, Long userId){
        DAOFactory daoFactory = DAOFactory.getInstance();
        daoFactory.beginTransaction();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        Voucher voucher = voucherDAO.getById(voucherId);
        User user = userDAO.getById(userId);
        Long userAccountId = user.getAccountId();
        Integer cost = voucher.getCost();
        AccountDAO accountDAO = daoFactory.getDao(AccountDAO.class);
        Account account = accountDAO.getById(userAccountId);

    }
}
