package kz.epam.servlet;

import kz.epam.dao.DAOException;
import kz.epam.dao.TourDAO;
import kz.epam.model.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("action");
        switch (actionName){
            case("create-tour"):
                String type = req.getParameter("tour-type");
                String cost = req.getParameter("tour-cost");
                Tour tour = new Tour();
                tour.setType(type);
                tour.setCost(cost);

                TourDAO tourDAO = new TourDAO();
                try {
                    tourDAO.insert(tour);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/tour-added.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
