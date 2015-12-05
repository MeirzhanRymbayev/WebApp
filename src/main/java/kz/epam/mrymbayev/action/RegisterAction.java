package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.CustomerDAO;
import kz.epam.mrymbayev.dao.DAOException;
import kz.epam.mrymbayev.model.Customer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action{
    Logger registLog = Logger.getLogger(RegisterAction.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        // TODO validate params
        // TODO check dublicate logins from DB
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(password);
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.insert(customer);
            registLog.info("Customer: " + customer.getLogin() + " was added to DB.");
        } catch (DAOException e) {
            registLog.error("DAOException was handled when application try insert(customer)");
            throw new DAOException();
        }
        return "VIEW";
    }
}
