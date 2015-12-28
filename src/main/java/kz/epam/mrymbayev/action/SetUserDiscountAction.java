package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetUserDiscountAction implements Action {
    Logger logger = Logger.getLogger("kz.epam.businessLogic");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOFactory daoFactory = DAOFactory.newInstance();

        String idString = request.getParameter("id");
        String discountString = request.getParameter("discount");
        double discount = Double.valueOf(discountString);
        long id = Long.valueOf(idString);
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        User user = userDAO.getById(id);
        user.setDiscount(discount);
        userDAO.save(user);
        logger.info("User discount was set: " + discountString);
        daoFactory.close();
        return "redirect:user-discount-was-set-successfully-page";
    }
}
