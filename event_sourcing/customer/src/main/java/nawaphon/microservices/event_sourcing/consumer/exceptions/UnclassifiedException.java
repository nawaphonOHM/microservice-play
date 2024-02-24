package nawaphon.microservices.event_sourcing.consumer.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnclassifiedException extends RuntimeException {
    private static final Logger LOGGER = LogManager.getLogger(UnclassifiedException.class);

    public UnclassifiedException(final String message, final Throwable exception) {
        super(message, exception);
        LOGGER.error("UnclassifiedException: " + message, exception);
    }

    public UnclassifiedException(final String message) {
        super(message);
        LOGGER.error("UnclassifiedException: " + message);
    }
}