package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.TourAgentDAO;
import kz.epam.mrymbayev.dao.exception.RdbCustomerDAOException;
import kz.epam.mrymbayev.dao.exception.RdbTourAgentDAOException;
import kz.epam.mrymbayev.model.TourAgent;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RdbTourAgentDAO implements TourAgentDAO {

    private Connection connection;
    private PropertyManager propertyManager = PropertyManager.getInstance();
    private Logger logger = Logger.getLogger(RdbTourAgentDAO.class);

    public RdbTourAgentDAO() {}

    public RdbTourAgentDAO(Connection connection, PropertyManager propertyManager) {
        this.connection = connection;
        this.propertyManager.loadProperties("query.properties");
    }

    @Override
    public TourAgent save(TourAgent tourAgent) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("tourAgent.insert");

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, tourAgent.getLogin());
            ps.setString(2, tourAgent.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            tourAgent.setId(id);
        } catch (SQLException e) {
            logger.error("Error with RdbTourAgentDAO insert() method");
            throw new RdbTourAgentDAOException("Error with RdbCustomerDAO insert() method");
        }
        return tourAgent;
    }

    @Override
    public TourAgent getByParameter(String param, String value) {
        return null;
    }

    @Override
    public TourAgent getById(Long id) {
        return null;
    }

    @Override
    public List<TourAgent> getAll() {
        return null;
    }

    @Override
    public boolean delete(TourAgent tourAgent) {
        return false;
    }
}
