package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.dao.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException;
}
