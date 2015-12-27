package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowSetUserDiscountPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.newInstance();
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        HttpSession session = request.getSession(false);
        List<User> users = userDAO.getAll();
        session.setAttribute("users", users);
        daoFactory.close();
        return "set-user-discount-page";
    }
}
