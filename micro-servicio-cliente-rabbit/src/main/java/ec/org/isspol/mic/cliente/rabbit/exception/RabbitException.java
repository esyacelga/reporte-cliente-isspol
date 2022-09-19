package ec.org.isspol.mic.cliente.rabbit.exception;

/**
 * Created by roberto.chasipanta on 6/22/2017.
 */
@SuppressWarnings("serial")
public class RabbitException extends Exception {
    public RabbitException() {
    }

    public RabbitException(String message) {
        super(message);
    }

    public RabbitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RabbitException(Throwable cause) {
        super(cause);
    }
}

