package nawaphon.microservices.transactional_outbox_pattern.order_service.service.implementation;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderSaveStatus;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderOutbox;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderOutboxRepository;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderOutboxRepository orderOutboxRepository;

    @InjectMocks
    private MainServiceImpl mainService;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Captor
    private ArgumentCaptor<OrderOutbox> orderOutboxCaptor;

    @Test
    @DisplayName("Should save order successfully")
    void saveOrderShouldSaveOrderSuccessfully() {
        // Arrange
        String orderName = "Test Order";
        BigDecimal price = BigDecimal.valueOf(100.0);
        UUID customerId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest(orderName, price, customerId);

        // Use Answer to simulate the behavior of the repository.save method
        when(orderRepository.save(any(Order.class))).thenAnswer(new Answer<Order>() {
            @Override
            public Order answer(InvocationOnMock invocation) {
                Order order = invocation.getArgument(0);
                // Call prePersist to simulate JPA behavior
                if (order.getOrderId() == null) {
                    order.prePersist();
                }
                return order;
            }
        });

        when(orderOutboxRepository.save(any(OrderOutbox.class))).thenAnswer(new Answer<OrderOutbox>() {
            @Override
            public OrderOutbox answer(InvocationOnMock invocation) {
                return invocation.getArgument(0);
            }
        });

        // Act
        OrderSaveStatus result = mainService.saveOrder(orderRequest);

        // Assert
        assertTrue(result.saveStatus());
        assertNotNull(result.orderId());

        // Verify order was saved correctly
        verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertEquals(orderName, capturedOrder.getOrderName());
        assertEquals(price, capturedOrder.getPrice());
        // Note: customerId is not used in the current implementation

        // Verify order outbox was saved correctly
        verify(orderOutboxRepository).save(orderOutboxCaptor.capture());
        OrderOutbox capturedOutbox = orderOutboxCaptor.getValue();
        assertNotNull(capturedOutbox);
        assertEquals("ORDER_SERVICE", capturedOutbox.getAggregatetype());
        assertEquals("NEW_ORDER", capturedOutbox.getType());
        assertNotNull(capturedOutbox.getPayload());

        // Verify payload contains order data
        Map<String, Object> payload = capturedOutbox.getPayload();
        assertNotNull(payload);
        assertEquals(orderName, payload.get("orderName"));
        assertEquals(price, payload.get("price"));
    }

    @Test
    @DisplayName("Should set order properties correctly")
    void saveOrderShouldSetOrderPropertiesCorrectly() {
        // Arrange
        String orderName = "Test Order";
        BigDecimal price = BigDecimal.valueOf(100.0);
        UUID customerId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest(orderName, price, customerId);

        // Use Answer to simulate the behavior of the repository.save method
        when(orderRepository.save(any(Order.class))).thenAnswer(new Answer<Order>() {
            @Override
            public Order answer(InvocationOnMock invocation) {
                Order order = invocation.getArgument(0);
                // Call prePersist to simulate JPA behavior
                if (order.getOrderId() == null) {
                    order.prePersist();
                }
                return order;
            }
        });

        when(orderOutboxRepository.save(any(OrderOutbox.class))).thenAnswer(new Answer<OrderOutbox>() {
            @Override
            public OrderOutbox answer(InvocationOnMock invocation) {
                return invocation.getArgument(0);
            }
        });

        // Act
        OrderSaveStatus result = mainService.saveOrder(orderRequest);

        // Assert
        assertTrue(result.saveStatus());
        assertNotNull(result.orderId());

        // Verify order was saved correctly
        verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertNotNull(capturedOrder);
        assertEquals(orderName, capturedOrder.getOrderName());
        assertEquals(price, capturedOrder.getPrice());

        // Verify order outbox was saved correctly
        verify(orderOutboxRepository).save(orderOutboxCaptor.capture());
        OrderOutbox capturedOutbox = orderOutboxCaptor.getValue();
        assertNotNull(capturedOutbox);
        assertEquals("ORDER_SERVICE", capturedOutbox.getAggregatetype());

        // Verify type property is set
        assertEquals("NEW_ORDER", capturedOutbox.getType());

        // Verify payload contains order data
        Map<String, Object> payload = capturedOutbox.getPayload();
        assertNotNull(payload);
        assertEquals(orderName, payload.get("orderName"));
        assertEquals(price, payload.get("price"));
    }
}
