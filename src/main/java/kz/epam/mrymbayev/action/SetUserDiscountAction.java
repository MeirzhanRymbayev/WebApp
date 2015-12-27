package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetUserDiscountAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("id");
        String discountString = request.getParameter("discount");
        double discount = Double.valueOf(discountString);
        System.out.println("discount = " + discount);
        long id = Long.valueOf(idString);
        DAOFactory daoFactory = DAOFactory.newInstance();
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        User user = userDAO.getById(id);
        user.setDiscount(discount);
        userDAO.save(user);
        daoFactory.close();
        return "redirect:user-discount-was-set-successfully-page";
    }
}
