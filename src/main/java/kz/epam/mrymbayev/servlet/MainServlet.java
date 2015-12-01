package kz.epam.mrymbayev.servlet;

import kz.epam.mrymbayev.dao.DAOException;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("action");
        switch (actionName){
            case("create-voucher"):
                String type = req.getParameter("voucher-type");
                String cost = req.getParameter("voucher-cost");
                Voucher voucher = new Voucher();
                voucher.setType(type);
                voucher.setCost(cost);

                VoucherDAO voucherDAO = new VoucherDAO();
                try {
                    voucherDAO.insert(voucher);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/voucher-added.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
