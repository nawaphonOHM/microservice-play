package nawaphon.microservices.event_sourcing.consumer.pojo;

public class Message {
    private final String message;

    public Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
