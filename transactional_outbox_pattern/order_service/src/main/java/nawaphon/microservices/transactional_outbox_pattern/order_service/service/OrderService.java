package nawaphon.microservices.transactional_outbox_pattern.order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderEvent;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderResponse;
import nawaphon.microservices.transactional_outbox_pattern.order_service.mapper.OrderMapper;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OutboxEvent;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderRepository;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for order operations with transactional outbox pattern.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    @Value("${outbox.kafka.topic}")
    private final String orderEventsTopic;

    /**
     * Create a new order and record an outbox event in the same transaction.
     *
     * @param request the order request
     * @return the created order
     */
    @Transactional
    public OrderResponse createOrder(final OrderRequest request) {
        // Create and save the order
        Order order = orderMapper.toEntity(request);
        order = orderRepository.save(order);

        // Create the order response
        final OrderResponse orderResponse = orderMapper.toResponse(order);

        // Create and save the outbox event
        createOutboxEvent(OrderEvent.orderCreated(orderResponse));

        log.info("Created order with ID: {}, Order Number: {}", order.getId(), order.getOrderNumber());
        return orderResponse;
    }

    /**
     * Update the status of an order and record an outbox event in the same transaction.
     *
     * @param orderNumber the order number
     * @param newStatus the new status
     * @return the updated order
     */
    @Transactional
    public OrderResponse updateOrderStatus(final String orderNumber, final OrderStatus newStatus) {
        // Find the order
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with number: " + orderNumber));

        // Record the previous status for the event
        final OrderStatus previousStatus = order.getStatus();

        // Update the status
        order.setStatus(newStatus);
        order = orderRepository.save(order);

        // Create the order response
        final OrderResponse orderResponse = orderMapper.toResponse(order);

        // Create and save the outbox event
        createOutboxEvent(OrderEvent.orderStatusChanged(orderResponse, previousStatus));

        log.info("Updated order status: {} -> {}, Order Number: {}", previousStatus, newStatus, orderNumber);
        return orderResponse;
    }

    /**
     * Get an order by its order number.
     *
     * @param orderNumber the order number
     * @return the order
     */
    public OrderResponse getOrder(final String orderNumber) {
        final Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with number: " + orderNumber));
        return orderMapper.toResponse(order);
    }

    /**
     * Get all orders.
     *
     * @return list of all orders
     */
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all orders for a specific customer.
     *
     * @param customerId the customer ID
     * @return list of orders for the customer
     */
    public List<OrderResponse> getOrdersByCustomer(final Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Create and save an outbox event.
     *
     * @param orderEvent the order event
     */
    private void createOutboxEvent(final OrderEvent orderEvent) {
        try {
            final String payload = objectMapper.writeValueAsString(orderEvent);

            final OutboxEvent outboxEvent = OutboxEvent.builder()
                    .aggregateType("Order")
                    .aggregateId(orderEvent.getOrderId().toString())
                    .eventType(orderEvent.getEventType())
                    .payload(payload)
                    .topic(orderEventsTopic)
                    .build();

            outboxEventRepository.save(outboxEvent);
            log.info("Created outbox event: {}, for order: {}", orderEvent.getEventType(), orderEvent.getOrderNumber());
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize order event", e);
            throw new RuntimeException("Failed to serialize order event", e);
        }
    }
}
