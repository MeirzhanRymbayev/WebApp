package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowMakeVoucherHotPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        HttpSession session = req.getSession(false);
        String locale = (String) session.getAttribute("locale");
        int intLocale = Util.localeConverter(locale);
        List<Voucher> vouchers = voucherDAO.getAllByLocale(intLocale);
        req.setAttribute("vouchers", vouchers);
        daoFactory.close();
        return "make-voucher-hot-page";
    }
}
