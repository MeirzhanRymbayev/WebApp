package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewVouchersAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute("locale");
        int intLocale = 2;
        if(locale.equals("kk")) intLocale = 1;
        if(locale.equals("ru")) intLocale = 2;
        if(locale.equals("en")) intLocale = 3;
        List<Voucher> vouchers = voucherDAO.getAllByLocale(intLocale);
        request.setAttribute("vouchers", vouchers);
        return "view-vouchers-page";
    }
}
