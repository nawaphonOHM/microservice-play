package nawaphon.microservices.transactional_outbox_pattern.order_service.service.implementation;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private MainServiceImpl mainService;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Test
    @DisplayName("Should save order successfully")
    void saveOrderShouldSaveOrderSuccessfully() {
        // Arrange
        String orderName = "Test Order";
        BigDecimal price = BigDecimal.valueOf(100.0);
        UUID customerId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest(orderName, price, customerId);
        
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        // Act
        boolean result = mainService.saveOrder(orderRequest);

        // Assert
        assertTrue(result);
        verify(orderRepository).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertEquals(orderName, savedOrder.getOrderName());
        assertEquals(price, savedOrder.getPrice());
        // Note: customerId is not used in the current implementation
    }

    @Test
    @DisplayName("Should set order properties correctly")
    void saveOrderShouldSetOrderPropertiesCorrectly() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Test Order", BigDecimal.valueOf(100.0), UUID.randomUUID());
        
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        // Act
        mainService.saveOrder(orderRequest);

        // Assert
        verify(orderRepository).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertNotNull(savedOrder);
        assertEquals("Test Order", savedOrder.getOrderName());
        assertEquals(BigDecimal.valueOf(100.0), savedOrder.getPrice());
    }
}