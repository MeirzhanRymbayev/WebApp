package kz.epam.mrymbayev.action.login;

import kz.epam.mrymbayev.action.Action;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null) {
            // TODO setError();
        }
        UserDAO UserDAO = DAOFactory.getInstance().getDao(UserDAO.class);
        User user = UserDAO.getByParameter("LOGIN", login);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:main-menu-page";
    }
}
