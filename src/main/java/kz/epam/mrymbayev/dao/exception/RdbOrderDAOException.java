package kz.epam.mrymbayev.dao.exception;

public class RdbOrderDAOException extends RuntimeException {
    public RdbOrderDAOException() {
        super();
    }

    public RdbOrderDAOException(String message) {
        super(message);
    }

    public RdbOrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbOrderDAOException(Throwable cause) {
        super(cause);
    }
}
