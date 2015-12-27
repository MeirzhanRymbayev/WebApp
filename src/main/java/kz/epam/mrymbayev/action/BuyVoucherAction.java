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
        String amountString = request.getParameter("amount");

        DAOFactory daoFactory = DAOFactory.newInstance();
        Long voucherId = Long.valueOf(idString);
        int amount = Integer.valueOf(amountString);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        VoucherService voucherService = new VoucherService(daoFactory);
        Voucher voucher = voucherService.getById(voucherId);
        boolean orderResult = voucherService.orderVoucher(voucher, user, amount);
        if(orderResult) logger.info("Voucher was successfully bought.");
        else logger.error("Voucher was not bought.");
        daoFactory.close();
        return "redirect:voucher-successfully-bought-page";
    }
}
