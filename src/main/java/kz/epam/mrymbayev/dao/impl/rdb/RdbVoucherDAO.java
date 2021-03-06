package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.dao.exception.RdbVoucherDAOException;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;
import kz.epam.mrymbayev.util.Util;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RdbVoucherDAO implements VoucherDAO {

    Logger logger = Logger.getLogger("kz.epam");

    Connection connection;
    PropertyManager propertyManager;

    public RdbVoucherDAO(Connection connection) {
        this.connection = connection;
        propertyManager = PropertyManager.getInstance();
        propertyManager.loadProperties("query.properties");
    }

    public Voucher insert(Voucher voucher) {

        String sql;
        try {
            sql = propertyManager.getProperty("voucher.insert");

            PreparedStatement ps1 = connection.prepareStatement(sql);
            ps1.setString(1, voucher.getHotel());
            ps1.setDate(2, voucher.getStartDate());
            ps1.setDate(3, voucher.getEndDate());
            ps1.setInt(4, voucher.getQuantity());
            ps1.setDouble(5, voucher.getDiscount());
            int rowCount = ps1.executeUpdate();
            if (rowCount == 1) logger.trace("Voucher was successfully inserted into VOUCHER table.");

            ResultSet rs = ps1.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            voucher.setId(id);
            String folderName = "/voucher" + voucher.getId();
            voucher.setFolderName(folderName);
            Util.saveFolderName(voucher, connection);
            ps1.close();
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with insert() operation.");
            throw new RdbVoucherDAOException("Issue with insert() operation.");
        }
        logger.trace("Voucher object was successfully inserted: " + voucher.toString());
        return voucher;
    }


    public Voucher insertI18nData(Voucher voucherI18nData){
        try {
        String sql2;
        sql2 = propertyManager.getProperty("voucherI18n.insert");
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        ps2.setLong(1, voucherI18nData.getId());
        ps2.setLong(2, voucherI18nData.getLocaleId());
        ps2.setString(3, voucherI18nData.getType());
        ps2.setInt(4, voucherI18nData.getCost());
        ps2.setString(5, voucherI18nData.getCountry());
        ps2.setString(6, voucherI18nData.getDayNightAmount());
        ps2.setString(7, voucherI18nData.getTransport());
        int rowCountPs2 = ps2.executeUpdate();
        if (rowCountPs2 == 1) logger.trace("Voucher was successfully inserted into VOUCHER_I18N table.");

        ResultSet rs2 = ps2.getGeneratedKeys();
        rs2.next();
        ps2.close();
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with insertI18n() operation.");
            throw new RdbVoucherDAOException("Issue with insertI18n() operation.");
        }
        logger.trace("Voucher object was successfully insertI18n: " + voucherI18nData.toString());
        return voucherI18nData;
    }

    public Voucher update(Voucher voucher) {
        try {
            String sql = propertyManager.getProperty("voucher.update");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, voucher.getHotel());
            ps.setDate(2, voucher.getStartDate());
            ps.setDate(3, voucher.getEndDate());
            ps.setInt(4, voucher.getQuantity());
            ps.setDouble(5, voucher.getDiscount());
            ps.setBoolean(6, voucher.isHot());
            ps.setLong(7, voucher.getId());
            int rowCount = ps.executeUpdate();
            if (rowCount == 1) logger.trace("Voucher cost was successfully updated into VOUCHER table.");
            ps.close();

        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with update() operation.");
            throw new RdbVoucherDAOException("Issue with update() operation.");
        }
        logger.trace("Voucher object was successfully updated: " + voucher.toString());
        return voucher;
    }

    public Voucher updateI18nData(Voucher voucher){
        String sql2 = propertyManager.getProperty("voucherI18n.update");
        try{
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        ps2.setInt(1, voucher.getCost());
        ps2.setLong(2, voucher.getId());
        int rowCountPs2 = ps2.executeUpdate();
        if (rowCountPs2 == 1) logger.trace("Voucher cost was successfully updated into VOUCHER_I18N table.");
        ps2.close();
    } catch (SQLException e) {
        logger.error("RdbVoucherDAOException with update() operation.");
        throw new RdbVoucherDAOException("Issue with update() operation.");
    }
    logger.trace("Voucher object was successfully updated: " + voucher.toString());
    return voucher;
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucher.isPersisted() ? update(voucher) : insert(voucher);
    }

    @Override
    public Voucher getByParameter(String param, String value) {
        Voucher voucher = new Voucher();
        String sql = propertyManager.getProperty("voucher.getByParameter");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, param);
            ps.setString(2, value);
            ResultSet resultSet = ps.executeQuery();
            voucher.setId(resultSet.getLong(1));
            voucher.setType(resultSet.getString(2));
            voucher.setCost(resultSet.getInt(3));
            ps.close();
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with getByParameter() operation.");
            throw new RdbVoucherDAOException("RdbVoucherDAOException with getByParameter() operation.");
        }
        return voucher;
    }

    @Override
    public Voucher getById(long id) {
        Voucher voucher = new Voucher();
        String sql = propertyManager.getProperty("voucher.getById");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            voucher.setId(rs.getLong("ID"));
            voucher.setHot(rs.getBoolean("IS_HOT"));
            voucher.setType(rs.getString("TYPE"));
            voucher.setCost(rs.getInt("COST"));
            voucher.setCountry(rs.getString("COUNTRY"));
            voucher.setCountry(rs.getString("DAY_NIGHT_AMOUNT"));
            voucher.setCountry(rs.getString("TRANSPORT"));
            voucher.setHotel(rs.getString("HOTEL"));
            voucher.setFolderName(rs.getString("FOLDER_NAME"));
            voucher.setStartDate(rs.getDate("START_DATE"));
            voucher.setEndDate(rs.getDate("END_DATE"));
            voucher.setQuantity(rs.getInt("QUANTITY"));
            voucher.setDiscount(rs.getDouble("DISCOUNT"));
            ps.close();
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with getById() operation.");
            throw new RdbVoucherDAOException("RdbVoucherDAOException with getById() operation.");
        }
        return voucher;
    }

    @Override
    public List<Voucher> getAll() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<Voucher> getAllByLocale(int locale) {
        List<Voucher> list = new ArrayList<>();
        final String sql = propertyManager.getProperty("voucher.getAllByLocale");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, locale);
            ResultSet rs = ps.executeQuery();
            Voucher voucher;
            while (rs.next()) {
                voucher = new Voucher();
                voucher.setId(rs.getLong("ID"));
                voucher.setType(rs.getString("TYPE"));
                voucher.setDiscount(rs.getDouble("DISCOUNT"));
                voucher.setCost(rs.getInt("COST"));
                voucher.setHotel(rs.getString("HOTEL"));
                voucher.setCountry(rs.getString("COUNTRY"));
                voucher.setDayNightAmount(rs.getString("DAY_NIGHT_AMOUNT"));
                voucher.setTransport(rs.getString("TRANSPORT"));
                voucher.setLocaleId(rs.getInt("LOCALE_ID"));
                voucher.setFolderName(rs.getString("FOLDER_NAME"));
                voucher.setQuantity(rs.getInt("QUANTITY"));
                voucher.setStartDate(rs.getDate("START_DATE"));
                voucher.setEndDate(rs.getDate("END_DATE"));
                String folder_name = rs.getString("FOLDER_NAME");
                voucher.setFileNames(Util.getFileNames(rs.getString("FOLDER_NAME")));
                voucher.setHot(rs.getBoolean("IS_HOT"));
                list.add(voucher);
            }
            ps.close();
        } catch (SQLException e) {
            logger.error("Error with RdbVoucherDAO getAllById() method");
            throw new RdbVoucherDAOException("Error with RdbVoucherDAO getAllById() method");
        }
        return list;
    }

    @Override
    public boolean delete(Voucher voucher) {
        final String sql = propertyManager.getProperty("customer.delete");
        boolean isDelete;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, voucher.getId());
            int affectedRows = ps.executeUpdate();
            isDelete = (affectedRows > 0) ? true : false;
        } catch (SQLException e) {
            logger.error("Error with RdbVoucherDAO delete() method");
            throw new RdbVoucherDAOException("Error with RdbVoucherDAO delete() method");
        }
        return isDelete;
    }
}
