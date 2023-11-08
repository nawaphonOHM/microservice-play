package nawaphon.microservices.customer_service.exceptions;

public class FailToSaveCustomerException extends RuntimeException {

    public String getStatusMessage() {
        return "failed";
    }

    public String getDetailMessage() {
        return "Data is fail to save on database";
    }
}
