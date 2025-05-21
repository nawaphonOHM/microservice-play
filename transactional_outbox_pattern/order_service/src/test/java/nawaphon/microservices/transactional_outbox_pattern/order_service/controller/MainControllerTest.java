package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.ResponseMessage;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MainService mainService;

    @Test
    @DisplayName("Should return success response when order is saved successfully")
    void testSaveOrderSuccess() throws Exception {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Test Order", BigDecimal.valueOf(100.0), UUID.randomUUID());
        when(mainService.saveOrder(any(OrderRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/save-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.results").value("Order saved successfully"));
    }

    @Test
    @DisplayName("Should return error response when order is not saved")
    void testSaveOrderFailure() throws Exception {
        // Arrange
        OrderRequest orderRequest = new OrderRequest("Test Order", BigDecimal.valueOf(100.0), UUID.randomUUID());
        when(mainService.saveOrder(any(OrderRequest.class))).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/save-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk()) // Note: The controller returns 500 in the response body, but HTTP status is still 200
                .andExpect(jsonPath("$.code").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value("Internal Server Error"))
                .andExpect(jsonPath("$.results").value("Order not saved"));
    }
}
