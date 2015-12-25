package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.*;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;

public class VoucherService {
    private DAOFactory daoFactory;
    private VoucherDAO voucherDAO;
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private OrderDAO orderDAO;
    private Voucher voucher;
    private User user;
    private Long accountId;
    private Account userAccount;
    private Order order;
    private static final Account companyAccount = new Account(1, 100L, 1_000_000);//если это статический объект ему автоиатический присваевается null

    public VoucherService() {
    }


    public boolean orderVoucher(long voucherId, long userId, int amount) {
        daoFactory = DAOFactory.getInstance();
        daoFactory.beginTransaction();
        voucherDAO = daoFactory.getDao(VoucherDAO.class);
        userDAO = daoFactory.getDao(UserDAO.class);
        accountDAO = daoFactory.getDao(AccountDAO.class);
        voucher = voucherDAO.getById(voucherId);
        user = userDAO.getById(userId);
        accountId = user.getAccountId();
        Integer cost = voucher.getCost();
        userAccount = accountDAO.getById(accountId);
        boolean transferred = transfer(cost, userAccount, companyAccount);
        System.out.println("transferred = " + transferred);
        boolean removed = removeGoodsFromResidue(amount, voucherId);
        System.out.println("removed = " + removed);
        boolean saving = saveOrder(voucher, user, amount);
        System.out.println("saving = " + saving);
        if (transferred && removed && saving) {
            //TODO предусмотреть есть ли доступные путевки с положительным количеством
            daoFactory.commit();
        } else {
            daoFactory.rollback();
            System.out.println("Rollback commit");
            return false;
        }
        return true;
    }

    private boolean removeGoodsFromResidue(int amount, long voucherId) {
        Voucher voucher = voucherDAO.getById(voucherId);
        System.out.println("voucher.getId() = " + voucher.getId());
        int residue = voucher.getQuantity();
        if (residue > amount) {
            residue = residue - amount;
            voucher.setQuantity(residue);
            voucherDAO.save(voucher);
        } else return false;
        return true;
    }

    private boolean saveOrder(Voucher voucher, User user, int amount) {
        order = new Order();
        order.setVoucherId(voucher.getId());
        order.setUserId(user.getId());
        order.setCost(voucher.getCost() * amount);
        order.setAmount(amount);
        order.setDiscount(user.getDiscount());
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));
        orderDAO = daoFactory.getDao(OrderDAO.class);
        orderDAO.save(order);
        //TODO check result of save method with if condition
        return true;
    }

    private boolean transfer(Integer cost, Account userAccount, Account companyAccount) {
        int userAsset = userAccount.getAsset();
        int companyAsset = companyAccount.getAsset();
        if (userAsset > 0 && userAsset > cost) {
            userAsset = userAsset - cost;
            companyAsset = companyAsset + cost;
            userAccount.setAsset(userAsset);
            companyAccount.setAsset(companyAsset);
            accountDAO.save(userAccount);
            accountDAO.save(companyAccount);
        } else return false;


        return true;
    }


}
