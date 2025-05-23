package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Should save an order")
    void shouldSaveOrder() {
        // Arrange
        Order order = new Order();
        order.setOrderName("Test Order");
        order.setPrice(BigDecimal.valueOf(100.0));

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        assertNotNull(savedOrder);
        assertEquals("Test Order", savedOrder.getOrderName());
        assertEquals(BigDecimal.valueOf(100.0), savedOrder.getPrice());
    }

    @Test
    @DisplayName("Should find order by ID")
    void shouldFindOrderById() {
        // Arrange
        UUID id = UUID.randomUUID();
        Order order = new Order();
        order.setId(id);
        order.setOrderName("Test Order");
        order.setPrice(BigDecimal.valueOf(100.0));

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        // Act
        Optional<Order> foundOrder = orderRepository.findById(id);

        // Assert
        assertTrue(foundOrder.isPresent());
        assertEquals(id, foundOrder.get().getId());
        assertEquals("Test Order", foundOrder.get().getOrderName());
    }

    @Test
    @DisplayName("Should return empty when order not found")
    void shouldReturnEmptyWhenOrderNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Order> foundOrder = orderRepository.findById(id);

        // Assert
        assertFalse(foundOrder.isPresent());
    }
}
