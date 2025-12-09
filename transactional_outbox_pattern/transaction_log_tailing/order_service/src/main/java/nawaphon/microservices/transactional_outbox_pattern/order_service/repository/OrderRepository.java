package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
}