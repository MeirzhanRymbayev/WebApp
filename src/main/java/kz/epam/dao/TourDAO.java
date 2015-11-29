package kz.epam.dao;

import kz.epam.model.Tour;

import java.sql.*;

public class TourDAO {

    public static final String INSERT_TOUR = "INSERT INTO TOUR (ID, TYPE, COST) VALUES (DEFAULT, ?, ?)";
    public static final int INSERT_TOUR_FIRST_PARAM = 1;
    public static final int INSERT_TOUR_SECOND_PARAM = 2;

    public Tour insert(Tour tour) throws DAOException {

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "as", "as");
            //TODO Create Connection Pool
            PreparedStatement ps = connection.prepareStatement(INSERT_TOUR);
            ps.setString(INSERT_TOUR_FIRST_PARAM, tour.getType());
            ps.setString(INSERT_TOUR_SECOND_PARAM, tour.getCost());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            tour.setId(id);


        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException();
        }

        return tour;

    }
}
