package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

/**
 * Repository for OutboxEvent entity operations.
 */
@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

    /**
     * Find an outbox event by its unique event ID.
     *
     * @param eventId the event ID
     * @return the outbox event if found
     */
    Optional<OutboxEvent> findByEventId(String eventId);

    /**
     * Find all unprocessed outbox events.
     * Uses pessimistic locking to prevent concurrent processing of the same events.
     *
     * @return list of unprocessed outbox events
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM OutboxEvent e WHERE e.processed = false ORDER BY e.createdAt ASC")
    List<OutboxEvent> findUnprocessedEventsWithLock();

    /**
     * Find all outbox events for a specific aggregate.
     *
     * @param aggregateType the aggregate type
     * @param aggregateId the aggregate ID
     * @return list of outbox events for the aggregate
     */
    List<OutboxEvent> findByAggregateTypeAndAggregateId(String aggregateType, String aggregateId);

    /**
     * Find all outbox events of a specific event type.
     *
     * @param eventType the event type
     * @return list of outbox events of the specified type
     */
    List<OutboxEvent> findByEventType(String eventType);

    /**
     * Find all unprocessed outbox events for a specific topic.
     *
     * @param topic the Kafka topic
     * @return list of unprocessed outbox events for the topic
     */
    List<OutboxEvent> findByTopicAndProcessedFalse(String topic);
}
