package kz.epam.mrymbayev.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowVoucherAddedAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "voucher-added";
    }
}
