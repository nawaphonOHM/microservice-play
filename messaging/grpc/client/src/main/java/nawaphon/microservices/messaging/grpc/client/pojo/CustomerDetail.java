package nawaphon.microservices.messaging.grpc.client.pojo;

import java.util.UUID;

public record CustomerDetail(UUID customerId, String firstName, String lastName) {

}
