package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewVouchersAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        HttpSession session = request.getSession(false);
        int locale = (int) session.getAttribute("locale");
        voucherDAO.getAllByLocale(locale);

        return "view-vouchers-page";
    }
}
