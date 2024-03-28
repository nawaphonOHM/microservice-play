package nawaphon.microservices.circuit_breaker.real_service.pojo;

public class ResponseMessage<A> {

    private final Number code;
    private final String message;
    private final A results;

    public ResponseMessage(final Number code, final String message, final A results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public A getResults() {
        return results;
    }

    public Number getCode() {
        return code;
    }
}
