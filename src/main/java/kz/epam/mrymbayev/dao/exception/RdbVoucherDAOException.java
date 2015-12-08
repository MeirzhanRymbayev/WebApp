package kz.epam.mrymbayev.dao.exception;

public class RdbVoucherDAOException extends RuntimeException {
    public RdbVoucherDAOException() {
        super();
    }

    public RdbVoucherDAOException(String message) {
        super(message);
    }

    public RdbVoucherDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbVoucherDAOException(Throwable cause) {
        super(cause);
    }
}
