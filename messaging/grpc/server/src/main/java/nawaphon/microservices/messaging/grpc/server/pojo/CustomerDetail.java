package nawaphon.microservices.messaging.grpc.server.pojo;

import java.util.UUID;

public record CustomerDetail(UUID customerId, String firstName, String lastName) {}
