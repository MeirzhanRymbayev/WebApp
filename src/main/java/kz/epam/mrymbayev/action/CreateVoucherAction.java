package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;
import kz.epam.mrymbayev.service.VoucherService;
import kz.epam.mrymbayev.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateVoucherAction implements Action {

    private int localeCount = 3;
    private DAOFactory daoFactory;
    Logger log = Logger.getLogger("kz.epam");
    Logger businessLog = Logger.getLogger("kz.epam.businessLogic");
    PropertyManager propertyManager = PropertyManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        daoFactory = DAOFactory.newInstance();
        log.info("create voucher action started.");
        propertyManager.loadProperties("app.properties");
        Voucher voucher = new Voucher();

        String[] type = request.getParameterValues("typeOfTour");
        String[] cost = request.getParameterValues("cost");
        String[] country = request.getParameterValues("country");
        String[] dayNightAmount = request.getParameterValues("dayNightAmount");
        String[] transport = request.getParameterValues("transport");
        String[] localeId = request.getParameterValues("localeId");
        String hotel = request.getParameter("hotel");
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
        } catch (ParseException ignored) {
            // Never will happened
        }

        Collection<Part> allParts = request.getParts();
        Collection<Part> files = getFiles(allParts);

        Map<String, String> formData = new HashMap<>();
        for (int i = 0; i < localeCount; i++) { // add all form data into map to validate
            formData.put("type", type[i]);
            formData.put("cost", cost[i]);
            formData.put("country", country[i]);
            formData.put("dayNightAmount", dayNightAmount[i]);
            formData.put("transport", transport[i]);
        }

        formData.put("hotel", hotel);
        formData.put("quantity", quantityString);
        if (files.size() == 0) request.setAttribute("uploadError", "Files were not found. Please, try again.");
        Map<String, String> violations = Validator.voucherCreateValidate(formData);
        if (violations.size() != 0) {
            for (Map.Entry<String, String> e :
                    violations.entrySet()) {
                request.setAttribute(e.getKey(), e.getValue());
            }
            return "create-voucher-page";
        }


        voucher.setHotel(hotel);
        voucher.setStartDate(new java.sql.Date(startDate.getTime()));
        voucher.setEndDate(new java.sql.Date(endDate.getTime()));
        voucher.setQuantity(quantity);


        VoucherService voucherService = new VoucherService(daoFactory);
        Voucher savedVoucher = voucherService.save(voucher);
        long savedVoucherId = savedVoucher.getId();
        Voucher voucherI18nData = new Voucher();
        for (int i = 0; i < localeCount; i++) {
            voucherI18nData.setId(savedVoucherId);
            voucherI18nData.setType(type[i]);
            voucherI18nData.setCost(Integer.parseInt(cost[i]));
            voucherI18nData.setCountry(country[i]);
            voucherI18nData.setDayNightAmount(dayNightAmount[i]);
            voucherI18nData.setTransport(transport[i]);
            voucherI18nData.setLocaleId(Integer.parseInt(localeId[i]));
            voucherService.saveI18nData(voucherI18nData);
        }


        businessLog.info("Voucher saved on database successfully.");
        String folderName = savedVoucher.getFolderName();
        String pathToCreatedFolder = saveFiles(files, folderName);
        log.trace("Files was successfully saved in " + pathToCreatedFolder + " directory.");
        daoFactory.close();
        return "redirect:voucher-added";
    }

    /**
     * Simple method, it copies from InputStream to OutputStream
     *
     * @param input  InputStream object
     * @param output OutputStream object
     * @throws IOException
     */
    private void copyStream(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    /**
     * Method saves files in path which configured by property file
     *
     * @param files      Files from web form
     * @param folderName Special folder name for voucher's files which generated by voucher's id number
     * @return Returns full path to folder with files
     * @throws IOException
     */
    private String saveFiles(Collection<Part> files, String folderName) throws IOException {
        propertyManager.loadProperties("app.properties");
        String imagesRootFolder = propertyManager.getProperty("voucher.images.root.folder");
        String fileFormat;
        String contentType;
        String pathToFile;

        File root = new File(imagesRootFolder);
        File newFolder = new File(root, folderName);
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

    /**
     * Method get from web form only file parts
     *
     * @param parts Web form parts
     * @return Returns only file parts from web form
     */
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
