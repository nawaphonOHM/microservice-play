package nawaphon.microservices.order_service.exceptions;

public class InsufficientException extends RuntimeException {

    public String getStatusMessage() {
        return "failed";
    }

    public String getDetailMessage() {
        return "Credit is insufficient";
    }
}
