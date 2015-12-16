package kz.epam.mrymbayev.action.login;

import kz.epam.mrymbayev.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return "sign-in-page";
    }
}
