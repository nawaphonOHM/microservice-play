package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.reusable.model.OrderOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderOutboxRepository extends JpaRepository<OrderOutbox, UUID> {
}