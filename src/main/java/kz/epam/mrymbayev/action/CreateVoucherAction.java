package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.exception.DAOException;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateVoucherAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter("voucher-type");
        String cost = request.getParameter("voucher-cost");
        Voucher voucher = new Voucher();
        voucher.setType(type);
        voucher.setCost(cost);

        VoucherDAO voucherDAO = new VoucherDAO();
        try {
            voucherDAO.insert(voucher);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:voucher-added";
    }
}
