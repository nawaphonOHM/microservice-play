package nawaphon.microservices.client_side_discovery.client.pojo;

public class ResponseStringMessage extends ResponseMessage<String> {

    public ResponseStringMessage(Number code, String message, String results) {
        super(code, message, results);
    }
}
