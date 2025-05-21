package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.ResponseMessage;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MainControllerTest {

    private MainController mainController;

    @Mock
    private MainService mainService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController(mainService);
    }

    @Test
    public void testSaveOrderSuccess() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Test Order", BigDecimal.valueOf(100.0), UUID.randomUUID());
        when(mainService.saveOrder(any(OrderRequest.class))).thenReturn(true);

        // Act
        ResponseMessage<String> response = mainController.saveOrder(orderRequest);

        // Assert
        assertEquals(HttpStatus.OK.value(), response.code());
        assertEquals("OK", response.message());
        assertEquals("Order saved successfully", response.results());
    }

    @Test
    public void testSaveOrderFailure() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Test Order", BigDecimal.valueOf(100.0), UUID.randomUUID());
        when(mainService.saveOrder(any(OrderRequest.class))).thenReturn(false);

        // Act
        ResponseMessage<String> response = mainController.saveOrder(orderRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.code());
        assertEquals("Internal Server Error", response.message());
        assertEquals("Order not saved", response.results());
    }
}
