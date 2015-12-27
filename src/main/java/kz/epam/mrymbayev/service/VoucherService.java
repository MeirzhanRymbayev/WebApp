package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.*;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;
import org.apache.log4j.Logger;

public class VoucherService {
    Logger logger = Logger.getLogger(VoucherService.class);

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


    public boolean orderVoucher(Voucher voucher, User user, int amount) {
        daoFactory = DAOFactory.newInstance();
        daoFactory.beginTransaction();
        accountDAO = daoFactory.getDao(AccountDAO.class);
        accountId = user.getAccountId();
        int cost = defineTotalCost(voucher, user);

        userAccount = accountDAO.getById(accountId);
        if (voucher.getQuantity() >= amount) {
            boolean transferred = transfer(cost, userAccount, companyAccount);
            System.out.println("transferred = " + transferred);
            boolean removed = removeGoodsFromResidue(amount, voucher);
            System.out.println("removed = " + removed);
            boolean saving = saveOrder(this.voucher, this.user, amount);
            System.out.println("saving = " + saving);
            if (transferred && removed && saving) {
                //TODO предусмотреть есть ли доступные путевки с положительным количеством
                daoFactory.commit();
                logger.info("Voucher was successfully bought.");
            } else {
                daoFactory.rollback();
                logger.error("Rollback commit");
                return false;
            }
        } else {
            logger.error("Vouchers quantity is lack for buying.");
            return false;
        }
        return true;
    }

    private int defineTotalCost(Voucher voucher, User user) {
        int cost = voucher.getCost();
        double discount;
        if (voucher.isHot()) {
            cost = voucher.getCost();
            discount = voucher.getDiscount();
            cost = (int) (cost - cost * discount);
        } else if (user.getDiscount() > 0) {
            cost = voucher.getCost();
            discount = user.getDiscount();
            cost = (int) (cost - cost * discount);
        }
        return cost;
    }

    private boolean removeGoodsFromResidue(int amount, Voucher voucher) {
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
        if (voucher.isHot()) {
            int cost = voucher.getCost();
            double discount = voucher.getDiscount();
            cost = (int) (cost - cost * discount) * amount;
            order.setCost(cost);
            order.setDiscount(voucher.getDiscount());

        } else if (user.getDiscount() > 0) {
            double discount = user.getDiscount();
            int cost = voucher.getCost();
            cost = (int) (cost - cost * discount) * amount;
            order.setCost(cost);
            order.setDiscount(user.getDiscount());
        }
        order.setAmount(amount);
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
