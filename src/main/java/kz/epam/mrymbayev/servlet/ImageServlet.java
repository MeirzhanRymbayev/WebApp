package kz.epam.mrymbayev.servlet;

import kz.epam.mrymbayev.pm.PropertyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "ImageServlet", urlPatterns = "/image/*")
public class ImageServlet extends HttpServlet {
    private PropertyManager pm = PropertyManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pm.loadProperties("app.properties");
        String rootFolder = pm.getProperty("voucher.images.root.folder");

        String pathInfo = request.getPathInfo();
        String imagePath = rootFolder;
        File image = new File(imagePath, pathInfo);
        String contentType = getServletContext().getMimeType(image.getName());

        if (contentType == null || !contentType.startsWith("image")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        Files.copy(image.toPath(), response.getOutputStream());
    }
}
