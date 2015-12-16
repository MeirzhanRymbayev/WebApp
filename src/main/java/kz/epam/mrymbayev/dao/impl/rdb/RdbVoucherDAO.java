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
        try {
            sql = propertyManager.getProperty("voucher.insert");

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, voucher.getType());
            ps.setInt(2, voucher.getCost());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            voucher.setId(id);
        } catch (SQLException e) {
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
        List<Voucher> list = new ArrayList<>();
        final String sql = propertyManager.getProperty("voucher.getAll");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Voucher voucher;
            while(resultSet.next()){
                voucher = new Voucher();
                voucher.setId(resultSet.getLong(1));
                voucher.setType(resultSet.getString(2));
                voucher.setCost(resultSet.getInt(3));
                list.add(voucher);
            }
        } catch (SQLException e) {
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
