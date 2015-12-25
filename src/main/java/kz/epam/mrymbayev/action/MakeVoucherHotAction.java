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

public class MakeVoucherHotAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String discount = req.getParameter("discount");



        return "redirect:voucher-was-made-hot-successfully-page";
    }
}
