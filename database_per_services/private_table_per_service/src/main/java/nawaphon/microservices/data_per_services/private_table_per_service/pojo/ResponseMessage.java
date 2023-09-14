package nawaphon.microservices.data_per_services.private_table_per_service.pojo;

public class ResponseMessage {

    private final String message;

    public ResponseMessage(final String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
