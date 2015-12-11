package kz.epam.mrymbayev.dao.exception;

public class RdbTourAgentDAOException extends RuntimeException {
    public RdbTourAgentDAOException() {
        super();
    }

    public RdbTourAgentDAOException(String message) {
        super(message);
    }

    public RdbTourAgentDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RdbTourAgentDAOException(Throwable cause) {
        super(cause);
    }
}
