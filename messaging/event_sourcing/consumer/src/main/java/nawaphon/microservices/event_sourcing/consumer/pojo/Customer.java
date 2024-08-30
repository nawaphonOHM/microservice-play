package nawaphon.microservices.event_sourcing.consumer.pojo;

import java.util.UUID;

public record Customer(UUID id, UUID detailsId) {

}
