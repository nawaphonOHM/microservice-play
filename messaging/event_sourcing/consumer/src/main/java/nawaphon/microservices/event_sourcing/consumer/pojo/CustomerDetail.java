package nawaphon.microservices.event_sourcing.consumer.pojo;

import java.util.UUID;

public record CustomerDetail(UUID customerId, String firstName, String lastName) {

}
