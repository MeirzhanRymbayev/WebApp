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
        // Тут получили путь до папки с изображениями
        String imagePath = rootFolder;
        File image = new File(imagePath, pathInfo);
        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
    }
}
