package kz.epam.mrymbayev.service;

import kz.epam.mrymbayev.dao.DAOFactory;
import kz.epam.mrymbayev.dao.UserDAO;
import kz.epam.mrymbayev.model.User;

public class UserService {

    private DAOFactory daoFactory;


    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User getBy(long id) {
        DAOFactory daoFactory = DAOFactory.newInstance();
        UserDAO userDAO = daoFactory.getDao(UserDAO.class);
        User user = userDAO.getById(id);
        daoFactory.close();
        return user;
    }
}
