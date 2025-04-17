package nawaphon.microservices.transactional_outbox_pattern.order_service.mapper;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderResponse;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Order entities and DTOs.
 */
@Component
public class OrderMapper {

    /**
     * Convert an OrderRequest DTO to an Order entity.
     *
     * @param request the order request
     * @return the order entity
     */
    public Order toEntity(final OrderRequest request) {
        return Order.builder()
                .customerId(request.getCustomerId())
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.CREATED)
                .build();
    }

    /**
     * Convert an Order entity to an OrderResponse DTO.
     *
     * @param order the order entity
     * @return the order response
     */
    public OrderResponse toResponse(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
