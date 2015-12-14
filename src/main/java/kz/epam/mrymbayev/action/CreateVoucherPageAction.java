package kz.epam.mrymbayev.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateVoucherPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "create-voucher-page";
    }
}
