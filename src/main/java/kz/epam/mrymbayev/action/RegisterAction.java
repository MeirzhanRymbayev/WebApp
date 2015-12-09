package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.impl.rdb.RdbCustomerDAO;
import kz.epam.mrymbayev.model.Customer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    Logger registLog = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // TODO validate params
        // TODO check dublicate logins from DB
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(password);
        RdbCustomerDAO rdbCustomerDAO = DAOFactory.getInstance().getDao(RdbCustomerDAO.class);

        rdbCustomerDAO.insert(customer);
        registLog.info("Customer: " + customer.getLogin() + " was added to DB.");

        return "redirect:register-success";
    }
}
