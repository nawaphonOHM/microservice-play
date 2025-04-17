package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO for creating a new order.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    /**
     * The ID of the customer placing the order.
     */
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    /**
     * The total amount of the order.
     */
    @NotNull(message = "Total amount is required")
    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
    private BigDecimal totalAmount;
}
