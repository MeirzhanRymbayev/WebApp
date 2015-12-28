package kz.epam.mrymbayev.servlet;

// Import required java libraries

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorHandler", urlPatterns = "/ErrorHandler")
public class ErrorHandler extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/error/error-page.jsp");
        requestDispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}