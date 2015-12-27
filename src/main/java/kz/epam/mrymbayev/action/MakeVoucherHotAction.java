package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeVoucherHotAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idString = req.getParameter("id");
        Long id = Long.valueOf(idString);
        String discountString = req.getParameter("discount");
        float discount = Float.valueOf(discountString);
        DAOFactory daoFactory = DAOFactory.newInstance();
        VoucherDAO voucherDAO = daoFactory.getDao(VoucherDAO.class);
        Voucher voucher = voucherDAO.getById(id);
        voucher.setDiscount(discount);
        voucher.setHot(true);
        voucherDAO.save(voucher);


        return "redirect:voucher-was-made-hot-successfully-page";
    }
}
