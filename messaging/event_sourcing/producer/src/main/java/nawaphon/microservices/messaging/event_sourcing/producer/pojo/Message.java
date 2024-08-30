package nawaphon.microservices.messaging.event_sourcing.producer.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(String message) {
    @JsonCreator
    public Message(@JsonProperty("message") final String message) {
        this.message = message;
    }
}
