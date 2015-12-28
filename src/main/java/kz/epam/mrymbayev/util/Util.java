package kz.epam.mrymbayev.util;

import kz.epam.mrymbayev.dao.exception.RdbVoucherDAOException;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class Util {


    /**
     * Method converts locale values from String to int
     * For example, 1 => kk, 2 => ru etc.
     * @param locale String value of locale
     * @return Returns int value of locale for the application
     */
    public static int localeConverter(String locale) {
        int intLocale = 0;
        switch (locale) {
            case "kk":
                intLocale = 1;
                break;
            case "ru":
                intLocale = 2;
                break;
            case "en":
                intLocale = 3;
                break;
        }
        return intLocale;
    }

    /**
     * Method scans folder and returns list of files names
     * @param voucherFolder Folder name which will be scanned
     * @return List of files names
     */
    public static List<String> getFileNames(String voucherFolder){
        PropertyManager pm = PropertyManager.getInstance();
        List<String> fileNames = new ArrayList<>();
        pm.loadProperties("app.properties");
        String rootFolder = pm.getProperty("voucher.images.root.folder");
        File dir = new File(rootFolder + voucherFolder + "/");
        File[] dirContent = dir.listFiles();
        //TODO if doesn't exist folder if(dirContent == null)
        for (File file :
                dirContent) {
            String fileName = "/" + file.getName();
            fileNames.add(fileName);
        }
        return fileNames;
    }

    /**
     * This method generate folder name by voucher id value.
     * @param savedVoucher Voucher which was inserted to DB.
     * @param connection Connection object.
     * @return Method returns true if folder name was successfully inserted to DB or false otherwise
     */
    public static boolean saveFolderName(Voucher savedVoucher, Connection connection) {
        Logger logger = Logger.getLogger(Util.class);
        PropertyManager propertyManager = PropertyManager.getInstance();
        String sql = propertyManager.getProperty("voucher.saveFolderName");
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, savedVoucher.getFolderName());
            ps.setLong(2, savedVoucher.getId());
            int i = ps.executeUpdate();
            ps.close();
            if (i != 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error with RdbVoucherDAO saveFolderName() method");
            throw new RdbVoucherDAOException("Error with RdbVoucherDAO saveFolderName() method");
        }
        return false;
    }
}
