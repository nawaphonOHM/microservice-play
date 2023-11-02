package nawaphon.microservice.shared_database.common.exceptions;

public class CustomerNotFoundException extends RuntimeException {


    public String getStatusMessage() {
        return "failed";
    }

    public String getDetailMessage() {
        return "Customer not found";
    }
}
