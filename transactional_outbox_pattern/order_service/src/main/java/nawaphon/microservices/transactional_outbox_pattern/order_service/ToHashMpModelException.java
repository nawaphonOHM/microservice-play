package nawaphon.microservices.transactional_outbox_pattern.order_service;

public class ToHashMpModelException extends RuntimeException {
    public ToHashMpModelException(final Throwable message) {
        super(message);
    }
}
