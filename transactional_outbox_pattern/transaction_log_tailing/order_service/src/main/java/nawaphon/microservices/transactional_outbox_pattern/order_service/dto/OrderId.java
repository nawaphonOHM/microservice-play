package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;


public record OrderId(UUID orderId) {
}