package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for order events that will be published to Kafka.
 * This is used in the Transactional Outbox Pattern.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEvent {

    /**
     * The type of event (e.g., "OrderCreated", "OrderStatusChanged").
     */
    private String eventType;

    /**
     * The order ID.
     */
    private Long orderId;

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
     * The previous status of the order (for status change events).
     */
    private OrderStatus previousStatus;

    /**
     * The current status of the order.
     */
    private OrderStatus currentStatus;

    /**
     * When the order was created.
     */
    private LocalDateTime createdAt;

    /**
     * When the order was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * When the event was created.
     */
    private LocalDateTime eventCreatedAt;

    /**
     * Factory method to create an OrderCreated event.
     *
     * @param order the order response DTO
     * @return the order event
     */
    public static OrderEvent orderCreated(OrderResponse order) {
        return OrderEvent.builder()
                .eventType("OrderCreated")
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .currentStatus(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .eventCreatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Factory method to create an OrderStatusChanged event.
     *
     * @param order the order response DTO
     * @param previousStatus the previous status
     * @return the order event
     */
    public static OrderEvent orderStatusChanged(OrderResponse order, OrderStatus previousStatus) {
        return OrderEvent.builder()
                .eventType("OrderStatusChanged")
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .previousStatus(previousStatus)
                .currentStatus(order.getStatus())
                .updatedAt(order.getUpdatedAt())
                .eventCreatedAt(LocalDateTime.now())
                .build();
    }
}