package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.*;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;

public class VoucherService {
    public boolean orderVoucher(Long voucherId, Long userId){
        DAOFactory daoFactory = DAOFactory.getInstance();
        daoFactory.beginTransaction();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        AccountDAO accountDAO = daoFactory.getDao(AccountDAO.class);
        Voucher voucher = voucherDAO.getById(voucherId);
        User user = userDAO.getById(userId);
        Long userAccountId = user.getAccountId();
        Integer cost = voucher.getCost();
        Account account = accountDAO.getById(userAccountId);
        Order order = new Order();
        order.setVoucherId(voucher.getId());
        order.setUserId(user.getId());
        order.setCost(voucher.getCost());
        order.setDiscount(user.getDiscount());
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));
        OrderDAO orderDAO = daoFactory.getDao(OrderDAO.class);
        orderDAO.save(order);

    }
}
