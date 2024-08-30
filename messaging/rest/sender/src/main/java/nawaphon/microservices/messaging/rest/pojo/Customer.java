package nawaphon.microservices.messaging.rest.pojo;

import java.util.UUID;

public record Customer(UUID id, UUID detailsId) {

}
