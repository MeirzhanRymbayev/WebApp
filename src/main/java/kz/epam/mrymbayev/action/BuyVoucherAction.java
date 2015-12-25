package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.service.VoucherService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyVoucherAction implements Action {
    Logger logger = Logger.getLogger(BuyVoucherAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("id");
        Long voucherId = Long.valueOf(idString);
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        Voucher voucher = voucherDAO.getById(voucherId);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        VoucherService voucherService = new VoucherService();
        String amountString = request.getParameter("amount");
        int amount = Integer.valueOf(amountString);
        boolean orderVoucherResult = voucherService.orderVoucher(voucher, user, amount);
        if(orderVoucherResult) logger.info("Voucher was successfully bought.");
        else logger.error("Voucher quantity is lack for buying it.");


        daoFactory.close();
        return "redirect:voucher-successfully-bought-page";
    }
}
