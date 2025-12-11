package nawaphon.microservices.messaging.grpc.server.pojo;

import java.util.UUID;

public record Customer(UUID id, UUID detailsId) {}
