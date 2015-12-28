package kz.epam.mrymbayev.dao.exception;

import java.sql.SQLException;

public class RdbUserDAOGetByParameterException extends Exception {

    public RdbUserDAOGetByParameterException(String s, SQLException e) {
    }

    public RdbUserDAOGetByParameterException() {
        super();
    }

    public RdbUserDAOGetByParameterException(String message) {
        super(message);
    }

    public RdbUserDAOGetByParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
