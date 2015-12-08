package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.VoucherDAO;
import kz.epam.mrymbayev.dao.exception.DAOException;
import kz.epam.mrymbayev.dao.exception.RdbVoucherDAOException;
import kz.epam.mrymbayev.model.Voucher;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RdbVoucherDAO implements VoucherDAO {

    public static final String INSERT_VOUCHER = "INSERT INTO VOUCHER (ID, TYPE, COST) VALUES (DEFAULT, ?, ?)";
    public static final String UPDATE_VOUCHER = "UPDATE VOUCHER SET TYPE = ?, COST = ? WHERE ID = ?;";
    public static final int INSERT_VOUCHER_FIRST_PARAM = 1;
    public static final int INSERT_VOUCHER_SECOND_PARAM = 2;

    Logger logger = Logger.getLogger(RdbVoucherDAO.class);

    Connection connection;

    public RdbVoucherDAO(Connection connection) {
        this.connection = connection;
    }

    public Voucher insert(Voucher voucher) {

        try {

            PreparedStatement ps = connection.prepareStatement(INSERT_VOUCHER);
            ps.setString(INSERT_VOUCHER_FIRST_PARAM, voucher.getType());
            ps.setString(INSERT_VOUCHER_SECOND_PARAM, voucher.getCost());
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
            PreparedStatement ps = connection.prepareStatement(UPDATE_VOUCHER);
            ps.setString(1, voucher.getType());
            ps.setString(2, voucher.getCost());
            ps.setLong(3, voucher.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("RdbVoucherDAOException with update() operation.");
            throw new RdbVoucherDAOException("Issue with update() operation.");
        }
        logger.trace("Voucher object was successfully updated: " + voucher.toString());
        return  voucher;
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucher.isPersisted() ? update(voucher) : insert(voucher);
    }

    @Override
    public Voucher getByParameter(String param, String value) {
        return null;
    }

    @Override
    public Voucher getById(Long id) {
        return null;
    }

    @Override
    public List<Voucher> getAll() {
        return null;
    }

    @Override
    public boolean delete(Voucher entity) {
        return false;
    }
}
