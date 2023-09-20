package nawaphon.microservices.data_per_services.private_table_per_service.pojo;

import java.util.List;

public class ResponseMessage<R> {

    private final Number code;
    private final String message;
    private final List<R> results;

    public ResponseMessage(final Number code, final String message, final List<R> results){
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public List<R> getResults() {
        return results;
    }

    public Number getCode() {
        return code;
    }
}
