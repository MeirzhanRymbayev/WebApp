package kz.epam.mrymbayev.dbpool;

public class ConnectionPoolException extends RuntimeException {
    //TODO почему ошибка исчезла от конструктора пула когда поставил рантайм
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
