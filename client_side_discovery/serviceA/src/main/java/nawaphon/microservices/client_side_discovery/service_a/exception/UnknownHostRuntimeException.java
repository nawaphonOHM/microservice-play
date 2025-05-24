package nawaphon.microservices.client_side_discovery.service_a.exception;

public class UnknownHostRuntimeException extends RuntimeException {
    public UnknownHostRuntimeException() {
        super("Unable to complete request");
    }
}
