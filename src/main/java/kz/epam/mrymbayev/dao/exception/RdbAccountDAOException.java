package kz.epam.mrymbayev.dao.exception;

public class RdbAccountDAOException extends RuntimeException {
    public RdbAccountDAOException() {
        super();
    }

    public RdbAccountDAOException(String message) {
        super(message);
    }

    public RdbAccountDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbAccountDAOException(Throwable cause) {
        super(cause);
    }
}
