package kz.epam.mrymbayev.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLocaleAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);

        String contextPath = request.getServletContext().getContextPath();
        System.out.println("contextPath = " + contextPath);

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        StringBuffer requestURL1 = request.getRequestURL();
        String requestURL = requestURL1.toString();
        System.out.println("requestURL = " + requestURL);
        return "main-menu";
    }
}
