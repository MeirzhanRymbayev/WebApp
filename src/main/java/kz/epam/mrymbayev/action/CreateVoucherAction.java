package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
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

        VoucherDAO voucherDAO = DAOFactory.getInstance().getDao(VoucherDAO.class);
        voucherDAO.save(voucher);
        return "redirect:voucher-added";
    }
}
