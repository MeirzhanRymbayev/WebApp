package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.CustomerDAO;
import kz.epam.mrymbayev.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login == null){
            // TODO setError();
        }
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.findByLogin(login);
        //TODO создать сессию и прикрутить в  рекуест
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        return "index.???forward";
    }
}
