package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(String orderName, BigDecimal price, UUID customerId) {
}
