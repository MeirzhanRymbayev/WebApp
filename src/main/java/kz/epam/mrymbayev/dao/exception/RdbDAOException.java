package kz.epam.mrymbayev.dao.exception;

public class RdbDAOException extends RuntimeException {
    public RdbDAOException() {
        super();
    }

    public RdbDAOException(String message) {
        super(message);
    }

    public RdbDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbDAOException(Throwable cause) {
        super(cause);
    }
}
