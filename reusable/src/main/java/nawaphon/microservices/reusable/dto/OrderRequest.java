package nawaphon.microservices.reusable.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(String orderName, BigDecimal price, UUID customerId) {
}
