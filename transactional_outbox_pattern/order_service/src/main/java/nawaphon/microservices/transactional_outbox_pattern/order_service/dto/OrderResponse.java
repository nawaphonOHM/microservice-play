package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for returning order information in API responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    /**
     * The order ID.
     */
    private Long id;

    /**
     * The unique order number.
     */
    private String orderNumber;

    /**
     * The ID of the customer who placed the order.
     */
    private Long customerId;

    /**
     * The total amount of the order.
     */
    private BigDecimal totalAmount;

    /**
     * The current status of the order.
     */
    private OrderStatus status;

    /**
     * When the order was created.
     */
    private LocalDateTime createdAt;

    /**
     * When the order was last updated.
     */
    private LocalDateTime updatedAt;
}