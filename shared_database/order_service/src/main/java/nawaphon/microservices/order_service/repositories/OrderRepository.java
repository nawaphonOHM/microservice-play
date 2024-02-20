package nawaphon.microservices.order_service.repositories;

import nawaphon.microservices.order_service.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByTotal(BigDecimal total);
}
