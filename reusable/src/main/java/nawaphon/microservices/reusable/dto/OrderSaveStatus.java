package nawaphon.microservices.reusable.dto;

import java.util.UUID;

public record OrderSaveStatus(UUID orderId, boolean saveStatus) {
}
