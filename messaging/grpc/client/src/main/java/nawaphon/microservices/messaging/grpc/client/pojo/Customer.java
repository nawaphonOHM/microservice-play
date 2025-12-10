package nawaphon.microservices.messaging.grpc.client.pojo;

import java.util.UUID;

public record Customer(UUID id, UUID detailsId) {

}
