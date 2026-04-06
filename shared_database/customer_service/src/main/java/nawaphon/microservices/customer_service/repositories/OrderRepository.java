package nawaphon.microservices.customer_service.repositories;

import nawaphon.microservices.reusable.entity.shared.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
