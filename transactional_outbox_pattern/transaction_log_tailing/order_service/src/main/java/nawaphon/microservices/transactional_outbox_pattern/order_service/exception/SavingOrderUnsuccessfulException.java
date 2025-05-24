package nawaphon.microservices.transactional_outbox_pattern.order_service.exception;

public class SavingOrderUnsuccessfulException extends RuntimeException {
    public SavingOrderUnsuccessfulException() {
        super("Failed to save order");
    }
}
