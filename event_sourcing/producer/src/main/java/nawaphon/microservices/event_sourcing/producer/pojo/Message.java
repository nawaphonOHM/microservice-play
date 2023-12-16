package nawaphon.microservices.event_sourcing.producer.pojo;

public class Message {
    private final String message;


    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
