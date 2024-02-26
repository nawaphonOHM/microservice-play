package nawaphon.microservices.event_sourcing.consumer.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnknownCustomerIdException extends RuntimeException {

    private static final Logger LOGGER = LogManager.getLogger(UnknownCustomerIdException.class);

    public UnknownCustomerIdException(final String message, final Throwable exception) {
        super(message, exception);
        LOGGER.error("UnknownCustomerIdException: " + message, exception);
    }

    public UnknownCustomerIdException(final String message) {
        super(message);
        LOGGER.error("UnknownCustomerIdException: " + message);
    }
}
