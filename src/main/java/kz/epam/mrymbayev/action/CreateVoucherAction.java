package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;
import kz.epam.mrymbayev.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

public class CreateVoucherAction implements Action {
    Logger log = Logger.getLogger(CreateVoucherAction.class);
    PropertyManager propertyManager = PropertyManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("create voucher action started.");
        propertyManager.loadProperties("app.properties");
        Voucher voucher = new Voucher();

        String type = request.getParameter("typeOfTour");
        String cost = request.getParameter("cost");
        String hotel = request.getParameter("hotel");
        String country = request.getParameter("country");
        String dayNightAmount = request.getParameter("dayNightAmount");
        String transport = request.getParameter("transport");
        String localeId = request.getParameter("localeId");
        String startDateString = request.getParameter("start-date");
        String endDateString = request.getParameter("end-date");
        String quantityString = request.getParameter("quantity");
        int quantity = Integer.valueOf(quantityString);
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            startDate = sdf.parse(startDateString);
            endDate = sdf.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Collection<Part> allParts = request.getParts();
        Collection<Part> files = getFiles(allParts);

        Map<String, String> formData = new HashMap<>();
        formData.put("type", type);
        formData.put("cost", cost);
        formData.put("hotel", hotel);
        formData.put("country", country);
        formData.put("dayNightAmount", dayNightAmount);
        formData.put("transport", transport);
        formData.put("quantity", quantityString);
        if(files.size() == 0) request.setAttribute("uploadError", "Files were not found. Please, try again.");
        Map<String, String> violations = Validator.voucherCreateValidate(formData);
        if(violations.size() != 0){
            for (Map.Entry<String, String> e :
                    violations.entrySet()) {
                request.setAttribute(e.getKey(), e.getValue());
            }
            return "create-voucher-page";
        }



        voucher.setType(type);
        voucher.setCost(Integer.parseInt(cost));
        voucher.setHotel(hotel);
        voucher.setCountry(country);
        voucher.setDayNightAmount(dayNightAmount);
        voucher.setTransport(transport);
        voucher.setLocaleId(Integer.parseInt(localeId));
        voucher.setStartDate(new java.sql.Date(startDate.getTime()));
        voucher.setEndDate(new java.sql.Date(endDate.getTime()));
        voucher.setQuantity(quantity);

        VoucherDAO voucherDAO = DAOFactory.getInstance().getDao(VoucherDAO.class);
        Voucher savedVoucher = voucherDAO.save(voucher);
        log.info("Voucher saved on database successfully.");

        String folderName = "/voucher" + savedVoucher.getId().toString();
        savedVoucher.setFolderName(folderName);
        voucherDAO.saveFolderName(savedVoucher);
        String pathToCreatedFolder = saveFiles(files, folderName);
        log.trace("Files was successfully saved in " + pathToCreatedFolder + " directory.");
        DAOFactory.getInstance().close();
        return "redirect:voucher-added";
    }

    private void copyStream(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private String saveFiles(Collection<Part> files, String randNewFolderName) throws IOException {
        propertyManager.loadProperties("app.properties");
        String imagesRootFolder = propertyManager.getProperty("voucher.images.root.folder");
        String fileFormat;
        String contentType;
        String pathToFile;

        File root = new File(imagesRootFolder);
        File newFolder = new File(root, randNewFolderName);
        newFolder.mkdir();
        for (Part file : files) {
            UUID nameUuid = UUID.randomUUID();
            //TODO don't change file name file.getName()
            contentType = file.getContentType();
            fileFormat = "." + contentType.substring(contentType.indexOf('/') + 1);
            pathToFile = newFolder + "/" + nameUuid + fileFormat;
            File file2 = new File(pathToFile);
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(file2);
            copyStream(is, os);
            is.close();
            os.close();
        }
        return newFolder.toString();
    }

    private Collection<Part> getFiles(Collection<Part> parts) {
        List<Part> partList = new ArrayList<>();
        for (Part part : parts) {
            if (part.getContentType() != null) {
                partList.add(part);
            }
        }
        return partList;
    }
}
