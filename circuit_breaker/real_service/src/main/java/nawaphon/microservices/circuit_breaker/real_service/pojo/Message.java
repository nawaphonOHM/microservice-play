package nawaphon.microservices.circuit_breaker.real_service.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(String message) {
    @JsonCreator
    public Message(@JsonProperty("message") final String message) {
        this.message = message;
    }
}
