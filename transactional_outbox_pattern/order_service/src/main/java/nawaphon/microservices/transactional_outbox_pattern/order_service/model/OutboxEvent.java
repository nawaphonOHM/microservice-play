package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an outbox event that needs to be published to Kafka.
 * This is a key component of the Transactional Outbox Pattern.
 */
@Entity
@Table(name = "outbox_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique event identifier
     */
    @Column(nullable = false, unique = true)
    private String eventId;

    /**
     * The aggregate type (e.g., "Order")
     */
    @Column(nullable = false)
    private String aggregateType;

    /**
     * The aggregate ID (e.g., the order ID)
     */
    @Column(nullable = false)
    private String aggregateId;

    /**
     * The type of event (e.g., "OrderCreated", "OrderStatusChanged")
     */
    @Column(nullable = false)
    private String eventType;

    /**
     * The event payload as JSON
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    /**
     * The Kafka topic to publish to
     */
    @Column(nullable = false)
    private String topic;

    /**
     * When the event was created
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * When the event was processed (published to Kafka)
     */
    private LocalDateTime processedAt;

    /**
     * Whether the event has been processed
     */
    @Column(nullable = false)
    private boolean processed;

    /**
     * Pre-persist hook to set default values before saving to the database.
     */
    @PrePersist
    public void prePersist() {
        if (eventId == null) {
            eventId = UUID.randomUUID().toString();
        }
        createdAt = LocalDateTime.now();
        processed = false;
    }
}
