package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.service.VoucherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyVoucherAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("id");
        Long voucherId = Long.valueOf(idString);
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        VoucherService voucherService = new VoucherService();
        String amountString = request.getParameter("amount");
        int amount = Integer.valueOf(amountString);
        boolean orderVoucherResult = voucherService.orderVoucher(voucherId, userId, amount);
        System.out.println(orderVoucherResult);


        daoFactory.close();
        return "redirect:voucher-successfully-bought-page";
    }
}
