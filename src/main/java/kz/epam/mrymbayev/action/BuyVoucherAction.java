package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyVoucherAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);




        daoFactory.close();
        return "voucher-successfully-bought-page";
    }
}
