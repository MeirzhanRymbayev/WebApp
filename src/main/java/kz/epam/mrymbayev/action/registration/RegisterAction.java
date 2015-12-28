package kz.epam.mrymbayev.action.registration;

import kz.epam.mrymbayev.action.Action;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RegisterAction implements Action {
    Logger registLog = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daoFactory = DAOFactory.newInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        // TODO validate params
        Map<String, String> formData = new HashMap<>();
        formData.put("login", login);
        formData.put("password", password);
        formData.put("firstname", firstname);
        formData.put("lastname", lastname);
        Map<String, String> violations = Validator.registerValidate(formData);
        if (violations.size() != 0) {
            for (Map.Entry<String, String> e : violations.entrySet()) {
                request.setAttribute(e.getKey(), e.getValue());
            }
            return "registration-page";
        }

        // TODO check dublicate logins from DB
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);

        userDAO.save(user);
        registLog.info("User: " + user.getLogin() + " was added to DB.");
        //TODO проверить правильно ли он вернет коннекшн
        daoFactory.close();
        return "redirect:register-success";
    }
}
