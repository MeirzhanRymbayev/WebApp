package kz.epam.mrymbayev.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowVoucherWasMadeHotPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "voucher-was-made-hot-successfully-page";
    }
}
