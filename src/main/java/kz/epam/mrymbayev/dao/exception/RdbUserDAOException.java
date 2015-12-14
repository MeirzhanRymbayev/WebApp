package kz.epam.mrymbayev.dao.exception;

public class RdbUserDAOException extends RuntimeException {
    public RdbUserDAOException() {
        super();
    }

    public RdbUserDAOException(String message) {
        super(message);
    }

    public RdbUserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbUserDAOException(Throwable cause) {
        super(cause);
    }
}
