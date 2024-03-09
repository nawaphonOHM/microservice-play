package nawaphon.microservices.event_sourcing.producer.pojo;

import java.util.UUID;

public class Customer {

    private final UUID id;


    public Customer(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
