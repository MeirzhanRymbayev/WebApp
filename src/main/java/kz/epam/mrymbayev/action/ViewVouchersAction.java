package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;
import kz.epam.mrymbayev.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewVouchersAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute("locale");
        int intLocale = Util.localeConverter(locale);
        List<Voucher> vouchers = voucherDAO.getAllByLocale(intLocale);
        request.setAttribute("vouchers", vouchers);
        daoFactory.close();

        return "view-vouchers-page";
    }


}

