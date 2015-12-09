package kz.epam.mrymbayev.dao.exception;

public class RdbCustomerDAOException extends RuntimeException {
    public RdbCustomerDAOException() {
        super();
    }

    public RdbCustomerDAOException(String message) {
        super(message);
    }

    public RdbCustomerDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbCustomerDAOException(Throwable cause) {
        super(cause);
    }
}
