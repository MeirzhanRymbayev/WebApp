package kz.epam.mrymbayev.action.registration;

import kz.epam.mrymbayev.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRegistrationPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "registration-page";
    }
}
