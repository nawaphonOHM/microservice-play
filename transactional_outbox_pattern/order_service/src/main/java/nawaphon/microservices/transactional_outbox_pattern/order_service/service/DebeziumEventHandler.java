package nawaphon.microservices.transactional_outbox_pattern.order_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderEvent;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderResponse;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * Service for handling Debezium change events.
 * This class processes change events captured by Debezium from the PostgreSQL database
 * and publishes them to Kafka.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DebeziumEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${outbox.kafka.topic}")
    private String orderEventsTopic;

    /**
     * Handles a Debezium change event.
     * This method is called by the Debezium engine when a change event is received.
     *
     * @param sourceRecord the source record from Debezium
     */
    public void handleChangeEvent(final SourceRecord sourceRecord) {
        try {
            // Extract the table name from the topic
            final String topic = sourceRecord.topic();
            final String tableName = topic.substring(topic.lastIndexOf('.') + 1);

            // Process the event based on the table
            if ("orders".equals(tableName)) {
                handleOrderChangeEvent(sourceRecord);
            } else if ("outbox_events".equals(tableName)) {
                // We can optionally process outbox events as well
                log.debug("Received outbox event change: {}", sourceRecord);
            } else {
                log.debug("Ignoring change event for table: {}", tableName);
            }
        } catch (final Exception e) {
            log.error("Error handling change event", e);
        }
    }

    /**
     * Handles a change event for the orders table.
     *
     * @param sourceRecord the source record from Debezium
     */
    private void handleOrderChangeEvent(final SourceRecord sourceRecord) throws IOException {
        // Extract the operation type (c=create, u=update, d=delete)
        final Struct value = (Struct) sourceRecord.value();
        final String op = value.getString("op");

        // Extract the after state of the record
        final Struct after = value.getStruct("after");
        if (after == null && !"d".equals(op)) {
            log.warn("After state is null for non-delete operation");
            return;
        }

        // For delete operations, we use the before state
        final Struct before = value.getStruct("before");
        if (before == null && "d".equals(op)) {
            log.warn("Before state is null for delete operation");
            return;
        }

        // Process based on operation type
        switch (op) {
            case "c": // Create
                processOrderCreated(after);
                break;
            case "u": // Update
                processOrderUpdated(before, after);
                break;
            case "d": // Delete
                processOrderDeleted(before);
                break;
            default:
                log.warn("Unknown operation type: {}", op);
        }
    }

    /**
     * Processes an order created event.
     *
     * @param after the after state of the record
     */
    private void processOrderCreated(final Struct after) throws IOException {
        // Convert the struct to an Order object
        final Order order = structToOrder(after);

        // Create an OrderResponse from the Order
        final OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();

        // Create an OrderCreated event
        final OrderEvent orderEvent = OrderEvent.orderCreated(orderResponse);

        // Publish the event to Kafka
        publishToKafka(orderEvent);

        log.info("Published OrderCreated event for order: {}", order.getOrderNumber());
    }

    /**
     * Processes an order updated event.
     *
     * @param before the before state of the record
     * @param after the after state of the record
     */
    private void processOrderUpdated(final Struct before, final Struct after) throws IOException {
        // Convert the structs to Order objects
        final Order beforeOrder = structToOrder(before);
        final Order afterOrder = structToOrder(after);

        // Check if the status has changed
        if (!beforeOrder.getStatus().equals(afterOrder.getStatus())) {
            // Create an OrderResponse from the after Order
            final OrderResponse orderResponse = OrderResponse.builder()
                    .id(afterOrder.getId())
                    .orderNumber(afterOrder.getOrderNumber())
                    .customerId(afterOrder.getCustomerId())
                    .totalAmount(afterOrder.getTotalAmount())
                    .status(afterOrder.getStatus())
                    .createdAt(afterOrder.getCreatedAt())
                    .updatedAt(afterOrder.getUpdatedAt())
                    .build();

            // Create an OrderStatusChanged event
            final OrderEvent orderEvent = OrderEvent.orderStatusChanged(orderResponse, beforeOrder.getStatus());

            // Publish the event to Kafka
            publishToKafka(orderEvent);

            log.info("Published OrderStatusChanged event for order: {}, status: {} -> {}", 
                    afterOrder.getOrderNumber(), beforeOrder.getStatus(), afterOrder.getStatus());
        } else {
            log.debug("Ignoring order update with no status change for order: {}", afterOrder.getOrderNumber());
        }
    }

    /**
     * Processes an order deleted event.
     *
     * @param before the before state of the record
     */
    private void processOrderDeleted(final Struct before) {
        // We could handle order deletion here if needed
        log.info("Order deleted: {}", before.getString("order_number"));
    }

    /**
     * Converts a Debezium struct to an Order object.
     *
     * @param struct the struct to convert
     * @return the Order object
     */
    private Order structToOrder(final Struct struct) {
        final Long id = struct.getInt64("id");
        final String orderNumber = struct.getString("order_number");
        final Long customerId = struct.getInt64("customer_id");
        final BigDecimal totalAmount = new BigDecimal(struct.getString("total_amount"));
        final OrderStatus status = OrderStatus.valueOf(struct.getString("status"));

        // Convert timestamps to LocalDateTime
        final LocalDateTime createdAt = instantToLocalDateTime(struct.getInt64("created_at") / 1000);
        final LocalDateTime updatedAt = instantToLocalDateTime(struct.getInt64("updated_at") / 1000);

        return Order.builder()
                .id(id)
                .orderNumber(orderNumber)
                .customerId(customerId)
                .totalAmount(totalAmount)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    /**
     * Converts an Instant to LocalDateTime.
     *
     * @param epochSecond the epoch second
     * @return the LocalDateTime
     */
    private LocalDateTime instantToLocalDateTime(final long epochSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault());
    }

    /**
     * Publishes an OrderEvent to Kafka.
     *
     * @param orderEvent the order event to publish
     */
    private void publishToKafka(final OrderEvent orderEvent) throws IOException {
        final String payload = objectMapper.writeValueAsString(orderEvent);
        kafkaTemplate.send(orderEventsTopic, orderEvent.getOrderNumber(), payload);
    }
}
