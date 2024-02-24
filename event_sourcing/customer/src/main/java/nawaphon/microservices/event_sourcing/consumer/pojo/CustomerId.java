package nawaphon.microservices.event_sourcing.consumer.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CustomerId {
    private final UUID id;

    public CustomerId(@JsonProperty("id") final UUID credit) {
        this.id = credit;
    }

    public UUID getId() {
        return id;
    }
}
