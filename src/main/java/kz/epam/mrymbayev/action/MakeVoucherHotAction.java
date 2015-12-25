package kz.epam.mrymbayev.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeVoucherHotAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String discount = req.getParameter("discount");


        return "redirect:voucher-was-made-hot-successfully-page";
    }
}
