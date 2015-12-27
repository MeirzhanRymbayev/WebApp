package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.*;
import kz.epam.mrymbayev.model.Account;
import kz.epam.mrymbayev.model.Order;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;
import org.apache.log4j.Logger;

public class VoucherService {
    Logger logger = Logger.getLogger(VoucherService.class);

    private Account companyAccount;
    private DAOFactory daoFactory;
    private VoucherDAO voucherDAO;
    private AccountDAO accountDAO;
    private int orderCost;
    private int amount;
    private double discount;

    public VoucherService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        AccountDAO accountDAO = daoFactory.getDao(AccountDAO.class);
        this.companyAccount = accountDAO.getById(1);
    }

    public Voucher getById(long id){
        DAOFactory daoFactory = DAOFactory.newInstance();
        voucherDAO = daoFactory.getDao(VoucherDAO.class);
        Voucher voucher = voucherDAO.getById(id);
        daoFactory.close();
        return voucher;
    }

    public boolean orderVoucher(Voucher voucher, User user, int amount) {
        this.amount = amount;
        daoFactory.beginTransaction();

        accountDAO = daoFactory.getDao(AccountDAO.class);
        long accountId = user.getAccountId();
        Account userAccount = accountDAO.getById(accountId);
        orderCost = defineIndividualOrderCost(voucher, user); // Cost with user's discount

        if (voucher.getQuantity() >= amount) {
            boolean transferred = transfer(orderCost, userAccount, companyAccount);
            logger.trace("Money transferred operation result: " + transferred);
            boolean removed = removeGoodsFromResidue(amount, voucher);
            logger.trace("Removing from residues result: " + removed);
            boolean saving = saveOrder(voucher, user, amount);
            logger.trace("Order saving operation result: " + saving);
            if (transferred && removed && saving) {
                daoFactory.commit();
                logger.info("Voucher was successfully bought.");
            } else {
                daoFactory.rollback();
                logger.error("Rollback commit was happened on ordering voucher operation.");
                return false;
            }
        } else {
            logger.error("Vouchers quantity is lack for buying.");
            return false;
        }
        return true;
    }

    private int defineIndividualOrderCost(Voucher voucher, User user) {
        int voucherPrice = voucher.getCost();
        discount = voucher.getDiscount();
        if (voucher.isHot()) {
            orderCost = (int) (voucherPrice - voucherPrice * discount) * amount;
        } else if (user.getDiscount() > 0) {
            discount = user.getDiscount();
            orderCost = (int) (orderCost - orderCost * discount) * amount;
        }
        return orderCost;
    }

    private boolean removeGoodsFromResidue(int amount, Voucher voucher) {
        System.out.println("voucher.getId() = " + voucher.getId());
        int residue = voucher.getQuantity();
        System.out.println("residue = " + residue);
        System.out.println("voucher = " + voucher);
        if (residue > amount) {
            residue = residue - amount;
            voucher.setQuantity(residue);
            System.out.println(voucher);
            Voucher voucher1 = voucherDAO.save(voucher);
            System.out.println("voucher1 = " + voucher1);
        } else return false;
        return true;
    }

    private boolean saveOrder(Voucher voucher, User user, int amount) {
        Order order = new Order();
        order.setVoucherId(voucher.getId());
        order.setUserId(user.getId());
        order.setCost(orderCost);
        order.setDiscount(discount);
        order.setAmount(amount);
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));
        OrderDAO orderDAO = daoFactory.getDao(OrderDAO.class);
        Order savedOrder = orderDAO.save(order);
        logger.trace("Order was saved on ORDERS table, order's ID is :" + savedOrder.getId());
        return true;
    }

    private boolean transfer(int cost, Account userAccount, Account companyAccount) {
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
