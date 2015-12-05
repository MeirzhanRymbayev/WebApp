package kz.epam.mrymbayev.dao;

import kz.epam.mrymbayev.jdcpool.ConnectionPool;
import kz.epam.mrymbayev.model.Voucher;

import java.sql.*;

public class VoucherDAO {

    public static final String INSERT_VOUCHER = "INSERT INTO VOUCHER (ID, TYPE, COST) VALUES (DEFAULT, ?, ?)";
    public static final int INSERT_VOUCHER_FIRST_PARAM = 1;
    public static final int INSERT_VOUCHER_SECOND_PARAM = 2;
    //TODO вынести в пропертя учетки. Доступны с ActionFactory
    public static final String JDBC_DB_URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String admin = "as";
    public static final String password = "as";

    public Voucher insert(Voucher voucher) throws DAOException {

        try {
            Class.forName("org.h2.Driver"); //TODO replace to ActionFactory
            Connection connection = ConnectionPool.getInstance().getConnection();
            //TODO Create Connection Pool
            PreparedStatement ps = connection.prepareStatement(INSERT_VOUCHER);
            ps.setString(INSERT_VOUCHER_FIRST_PARAM, voucher.getType());
            ps.setString(INSERT_VOUCHER_SECOND_PARAM, voucher.getCost());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            voucher.setId(id);


        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException();
        }

        return voucher;

    }
}
