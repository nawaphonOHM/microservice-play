package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;


import java.util.UUID;


public record OrderId(UUID orderId) {
}