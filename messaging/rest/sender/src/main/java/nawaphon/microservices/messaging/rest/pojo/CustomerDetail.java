package nawaphon.microservices.messaging.rest.pojo;

import java.util.UUID;

public record CustomerDetail(UUID customerId, String firstName, String lastName) {

}
