package kz.epam.mrymbayev.action;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLocaleAction implements Action {
    Logger logger = Logger.getLogger("kz.epam.businessLogic");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);
        logger.trace("Locale was set: " + locale);
        String referer = request.getHeader("Referer");
        String currentPage = getReferrerPage(referer);
        if (!currentPage.contains("page")) return "main-menu-page";
        return currentPage;
    }

    /**
     * This method define referrer page(action) from request header
     * and returns it.
     *
     * @param referrer Referrer string from request's header
     * @return Returns referrer action, which send client to current page he was.
     */
    private String getReferrerPage(String referrer) {
        int questionMarkIndex = referrer.indexOf("?");
        int actionParamStartIndex = questionMarkIndex + 8;
        boolean containsTwoAndMoreParams = referrer.contains("&");
        String respPage;
        if (containsTwoAndMoreParams) {
            respPage = referrer.substring(actionParamStartIndex, referrer.indexOf("&"));
        } else {
            respPage = referrer.substring(actionParamStartIndex);
        }
        return respPage;
    }


}
