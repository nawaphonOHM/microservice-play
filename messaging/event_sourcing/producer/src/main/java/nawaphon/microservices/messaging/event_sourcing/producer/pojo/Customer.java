package nawaphon.microservices.messaging.event_sourcing.producer.pojo;

import java.util.UUID;

public class Customer {

    private final UUID id;

    private final UUID detailsId;


    public Customer(final UUID id, final UUID detailsId) {
        this.id = id;
        this.detailsId = detailsId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getDetailsId() {
        return detailsId;
    }
}
