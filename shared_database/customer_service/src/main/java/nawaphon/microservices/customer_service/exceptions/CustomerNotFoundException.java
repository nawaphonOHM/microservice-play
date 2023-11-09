package nawaphon.microservices.customer_service.exceptions;

public class CustomerNotFoundException extends RuntimeException {


    public String getStatusMessage() {
        return "failed";
    }

    public String getDetailMessage() {
        return "Customer not found";
    }
}
