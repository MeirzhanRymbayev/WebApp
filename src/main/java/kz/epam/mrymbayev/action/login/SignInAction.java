package kz.epam.mrymbayev.action.login;

import kz.epam.mrymbayev.action.Action;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.dao.exception.RdbUserDAOGetByParameterException;
import kz.epam.mrymbayev.model.User;
import kz.epam.mrymbayev.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SignInAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daoFactory = DAOFactory.newInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Map<String, String> formData = new HashMap<>();
        formData.put("login", login);
        formData.put("password", password);
        Map<String, String> violations = Validator.signInValidator(formData);

        if (violations.size() != 0) {
            for (Map.Entry<String, String> e :
                    violations.entrySet()) {
                request.setAttribute(e.getKey(), e.getValue());
            }
            return "sign-in-page";
        }
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        User user;
        try {
            user = userDAO.getByParameter("LOGIN", login);
        } catch (RdbUserDAOGetByParameterException e) {
            request.setAttribute("unknownLoginError", "Incorrect login or password. Please, enter correct data.");
            return "sign-in-page";
        }

        HttpSession guestSession = request.getSession(false);
        User guestUser = (User) guestSession.getAttribute("user");
        if (guestUser.getRole().getName().equals("guest")) {
            guestSession.removeAttribute("user");
        }
        HttpSession session = request.getSession(false);
        session.setAttribute("user", user);
        if (session.getAttribute("roleError") != null) {
            session.removeAttribute("roleError");
        }

        /* If role of user equals MANAGER then we redirect user to manage-index-page */
        if (user.getRole().getName().equals("manager")) {
            return "redirect:manage-index-page";
        }
        daoFactory.close();
        return "redirect:main-menu-page";
    }
}
