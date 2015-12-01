package kz.epam.mrymbayev.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // TODO validate params
        // TODO check dublicate logins from DB
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(password);
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.insert(customer);
    }
}
