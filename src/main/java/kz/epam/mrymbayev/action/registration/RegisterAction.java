package kz.epam.mrymbayev.action.registration;

import kz.epam.mrymbayev.action.Action;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.model.User;
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
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        UserDAO userDAO = DAOFactory.getInstance().getDao(UserDAO.class);

        userDAO.save(user);
        registLog.info("User: " + user.getLogin() + " was added to DB.");



        return "redirect:register-success";
    }
}
