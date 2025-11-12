package nawaphon.microservices.transactional_outbox_pattern.order_service.repository;

import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderOutbox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderOutboxRepositoryTest {

    @Mock
    private OrderOutboxRepository orderOutboxRepository;

    @Test
    @DisplayName("Should save an order outbox")
    void shouldSaveOrderOutbox() {
        // Arrange
        var orderOutbox = new OrderOutbox();
        orderOutbox.setAggregatetype("ORDER_SERVICE");
        orderOutbox.setType("NEW_ORDER");
        
        var payload = new HashMap<String, Object>();
        payload.put("orderName", "Test Order");
        payload.put("price", 100.0);
        orderOutbox.setPayload(payload);

        when(orderOutboxRepository.save(any(OrderOutbox.class))).thenReturn(orderOutbox);

        // Act
        var savedOrderOutbox = orderOutboxRepository.save(orderOutbox);

        // Assert
        assertNotNull(savedOrderOutbox);
        assertEquals("ORDER_SERVICE", savedOrderOutbox.getAggregatetype());
        assertEquals("NEW_ORDER", savedOrderOutbox.getType());
        assertNotNull(savedOrderOutbox.getPayload());
        assertEquals("Test Order", savedOrderOutbox.getPayload().get("orderName"));
        assertEquals(100.0, savedOrderOutbox.getPayload().get("price"));
    }

    @Test
    @DisplayName("Should find order outbox by ID")
    void shouldFindOrderOutboxById() {
        // Arrange
        var id = UUID.randomUUID();
        var orderOutbox = new OrderOutbox();
        orderOutbox.setId(id);
        orderOutbox.setAggregatetype("ORDER_SERVICE");
        orderOutbox.setType("NEW_ORDER");
        
        var payload = new HashMap<String, Object>();
        payload.put("orderName", "Test Order");
        payload.put("price", 100.0);
        orderOutbox.setPayload(payload);

        when(orderOutboxRepository.findById(id)).thenReturn(Optional.of(orderOutbox));

        // Act
        var foundOrderOutbox = orderOutboxRepository.findById(id);

        // Assert
        assertTrue(foundOrderOutbox.isPresent());
        assertEquals(id, foundOrderOutbox.get().getId());
        assertEquals("ORDER_SERVICE", foundOrderOutbox.get().getAggregatetype());
        assertEquals("NEW_ORDER", foundOrderOutbox.get().getType());
        assertNotNull(foundOrderOutbox.get().getPayload());
        assertEquals("Test Order", foundOrderOutbox.get().getPayload().get("orderName"));
        assertEquals(100.0, foundOrderOutbox.get().getPayload().get("price"));
    }

    @Test
    @DisplayName("Should return empty when order outbox not found")
    void shouldReturnEmptyWhenOrderOutboxNotFound() {
        // Arrange
        var id = UUID.randomUUID();
        when(orderOutboxRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        var foundOrderOutbox = orderOutboxRepository.findById(id);

        // Assert
        assertFalse(foundOrderOutbox.isPresent());
    }
}