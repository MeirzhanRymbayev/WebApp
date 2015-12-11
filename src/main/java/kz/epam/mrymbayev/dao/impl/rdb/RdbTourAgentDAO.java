package kz.epam.mrymbayev.dao.impl.rdb;

import kz.epam.mrymbayev.dao.TourAgentDAO;
import kz.epam.mrymbayev.dao.exception.RdbCustomerDAOException;
import kz.epam.mrymbayev.dao.exception.RdbTourAgentDAOException;
import kz.epam.mrymbayev.model.TourAgent;
import kz.epam.mrymbayev.pm.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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
            return tourAgent.isPersisted() ? update(tourAgent) : insert(tourAgent);
    }

    private TourAgent update(TourAgent tourAgent) {
        PreparedStatement ps;
        String sql = propertyManager.getProperty("tourAgent.update");
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, tourAgent.getLogin());
            ps.setString(2, tourAgent.getPassword());
            ps.setLong(3, tourAgent.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error with RdbTourAgentDAO update() method");
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO insert() method");
        }
        return tourAgent;
    }

    public TourAgent insert(TourAgent tourAgent){
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
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO insert() method");
        }
        return tourAgent;
    }

    @Override
    public TourAgent getByParameter(String param, String value) {
        String sql = propertyManager.getProperty("tourAgent.getByParameter");
        TourAgent tourAgent = new TourAgent();
        try {
            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery("SELECT * FROM TOUR_AGENT WHERE "+ param +" = '"+value+"'");
            rs.next();
            tourAgent.setId(rs.getLong(1));
            tourAgent.setLogin(rs.getString("LOGIN"));
            tourAgent.setPassword(rs.getString("PASSWORD"));

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error with RdbTourAgentDAO getByParameter() method");
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO getByParameter() method");
        }
        return tourAgent;
    }

    @Override
    public TourAgent getById(Long id) {
        String sql = propertyManager.getProperty("tourAgent.getById");
        TourAgent tourAgent = new TourAgent();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            tourAgent.setId(rs.getLong(1));
            tourAgent.setLogin(rs.getString(2));
            tourAgent.setPassword(rs.getString(3));
        } catch (SQLException e) {
            logger.error("Error with RdbTourAgentDAO getById() method");
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO getById() method");
        }
        return tourAgent;
    }

    @Override
    public List<TourAgent> getAll() {
        List<TourAgent> list = new ArrayList<>();
        final String sql = propertyManager.getProperty("tourAgent.getAll");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            TourAgent tourAgent;
            while(resultSet.next()){
                tourAgent = new TourAgent();
                tourAgent.setId(resultSet.getLong(1));
                tourAgent.setLogin(resultSet.getString(2));
                tourAgent.setPassword(resultSet.getString(3));
                //TODO check this condition
                if(resultSet.getString(4) != null){
                    tourAgent.setVoucherId(resultSet.getLong(4));
                }
                list.add(tourAgent);
            }
        } catch (SQLException e) {
            logger.error("Error with RdbTourAgentDAO getAll() method");
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO getAll() method");
        }
        return list;
    }

    @Override
    public boolean delete(TourAgent tourAgent) {
        final String sql = propertyManager.getProperty("tourAgent.delete");
        boolean isDelete;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, tourAgent.getId());
            int affectedRows = ps.executeUpdate();
            isDelete = (affectedRows > 0) ? true : false;
        } catch (SQLException e) {
            logger.error("Error with RdbTourAgentDAO delete() method");
            throw new RdbTourAgentDAOException("Error with RdbTourAgentDAO delete() method");
        }
        return isDelete;
    }
}
