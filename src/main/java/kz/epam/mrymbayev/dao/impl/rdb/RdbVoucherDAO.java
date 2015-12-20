package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.dao.exception.RdbVoucherDAOException;
import kz.epam.mrymbayev.model.Voucher;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RdbVoucherDAO implements VoucherDAO {

    Logger logger = Logger.getLogger(RdbVoucherDAO.class);

    Connection connection;
    PropertyManager propertyManager;

    public RdbVoucherDAO(Connection connection) {
        this.connection = connection;
        propertyManager = PropertyManager.getInstance();
        propertyManager.loadProperties("query.properties");
    }

    public Voucher insert(Voucher voucher) {

        final String sql;
        final String sql2;
        try {
            sql = propertyManager.getProperty("voucher.insert");

            PreparedStatement ps1 = connection.prepareStatement(sql);
            ps1.setString(1, voucher.getHotel());
            ps1.executeUpdate();

            ResultSet rs = ps1.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            voucher.setId(id);
            ps1.close();

            sql2 = propertyManager.getProperty("voucherI18n.insert");
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setLong(1, id);
            ps2.setLong(2, voucher.getLocaleId());
            ps2.setString(3, voucher.getType());
            ps2.setInt(4, voucher.getCost());
            ps2.setString(5, voucher.getCountry());
            ps2.setString(6, voucher.getDayNightAmount());
            ps2.setString(7, voucher.getTransport());
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            rs2.next();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("RdbVoucherDAOException with insert() operation.");
            throw new RdbVoucherDAOException("Issue with insert() operation.");
        }
        logger.trace("Voucher object was successfully inserted: " + voucher.toString());
        return voucher;
    }

    public Voucher update(Voucher voucher) {
        try {
            final String sql = propertyManager.getProperty("voucher.update");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, voucher.getType());
            ps.setInt(2, voucher.getCost());
            ps.setLong(3, voucher.getId());
            ps.executeUpdate();
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
        final String sql = propertyManager.getProperty("voucher.getByParameter");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, param);
            ps.setString(2, value);
            ResultSet resultSet = ps.executeQuery();
            voucher.setId(resultSet.getLong(1));
            voucher.setType(resultSet.getString(2));
            voucher.setCost(resultSet.getInt(3));
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with getByParameter() operation.");
            throw new RdbVoucherDAOException("RdbVoucherDAOException with getByParameter() operation.");
        }
        return voucher;
    }

    @Override
    public Voucher getById(Long id) {
        Voucher voucher = new Voucher();
        final String sql = propertyManager.getProperty("voucher.getById");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            voucher.setId(resultSet.getLong(1));
            voucher.setType(resultSet.getString(2));
            voucher.setCost(resultSet.getInt(3));
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with getById() operation.");
            throw new RdbVoucherDAOException("RdbVoucherDAOException with getById() operation.");
        }
        return voucher;
    }

    @Override
    public List<Voucher> getAll() {
        return null;
    }

    @Override
    public List<Voucher> getAllByLocale(int locale) {
        List<Voucher> list = new ArrayList<>();
        final String sql = propertyManager.getProperty("voucher.getAll");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, locale);
            ResultSet rs = ps.executeQuery();
            Voucher voucher;
            while(rs.next()){
                voucher = new Voucher();
                voucher.setId(rs.getLong("ID"));
                voucher.setType(rs.getString("TYPE"));
                voucher.setCost(rs.getInt("COST"));
                voucher.setHotel(rs.getString("HOTEL"));
                voucher.setCountry(rs.getString("COUNTRY"));
                voucher.setDayNightAmount(rs.getString("DAY_NIGHT_AMOUNT"));
                voucher.setTransport(rs.getString("TRANSPORT"));
                voucher.setLocaleId(rs.getInt("LOCALE_ID"));
                list.add(voucher);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error with RdbVoucherDAO getAll() method");
            throw new RdbVoucherDAOException("Error with RdbVoucherDAO getAll() method");
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
