package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.CustomerDAO;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.impl.rdb.RdbCustomerDAO;
import kz.epam.mrymbayev.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null) {
            // TODO setError();
        }
        CustomerDAO CustomerDAO = DAOFactory.getInstance().getDao(CustomerDAO.class);
        Customer customer = CustomerDAO.getByParameter("LOGIN", login);

        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        return "redirect:main-menu";
    }
}
