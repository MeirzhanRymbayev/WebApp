package kz.epam.mrymbayev.servlet;

import kz.epam.mrymbayev.action.Action;
import kz.epam.mrymbayev.action.ActionException;
import kz.epam.mrymbayev.action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
@MultipartConfig
public class MainServlet extends HttpServlet {
    ActionFactory actionFactory;
    Logger log = Logger.getLogger(MainServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            actionFactory = new ActionFactory();
        } catch (ActionException e) {
            log.error("ActionFactory exception was happened");
            throw new ServletException();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = actionFactory.getAction(req);
        String view = action.execute(req, resp);
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (view.startsWith("redirect:")) {
            resp.sendRedirect(getRedirectLocation(view));
            return;
        }
        req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req, resp);
    }

    private String getRedirectLocation(String view) {
        return getServletContext().getContextPath() + "/" + "controller?action=" + view.substring(9);
    }

}
