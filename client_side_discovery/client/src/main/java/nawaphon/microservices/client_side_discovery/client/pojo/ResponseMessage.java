package nawaphon.microservices.client_side_discovery.client.pojo;

public class ResponseMessage<A> {
    private Number code;
    private String message;
    private A results;

    public ResponseMessage(Number code, String message, A results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public Number getCode() {
        return code;
    }

    public void setCode(Number code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public A getResults() {
        return results;
    }

    public void setResults(A results) {
        this.results = results;
    }
}
