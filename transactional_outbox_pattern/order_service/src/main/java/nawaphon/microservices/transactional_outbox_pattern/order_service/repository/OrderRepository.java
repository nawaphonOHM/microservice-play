package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order entity operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find an order by its unique order number.
     *
     * @param orderNumber the order number
     * @return the order if found
     */
    Optional<Order> findByOrderNumber(String orderNumber);

    /**
     * Find all orders for a specific customer.
     *
     * @param customerId the customer ID
     * @return list of orders for the customer
     */
    List<Order> findByCustomerId(Long customerId);

    /**
     * Find all orders with a specific status.
     *
     * @param status the order status
     * @return list of orders with the specified status
     */
    List<Order> findByStatus(OrderStatus status);
}