package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import lombok.RequiredArgsConstructor;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderResponse;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderStatus;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST controller for order operations.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Create a new order.
     *
     * @param request the order request
     * @return the created order
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody final OrderRequest request) {
        final OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update the status of an order.
     *
     * @param orderNumber the order number
     * @param status the new status
     * @return the updated order
     */
    @PutMapping("/{orderNumber}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable final String orderNumber,
            @RequestParam final OrderStatus status) {
        final OrderResponse response = orderService.updateOrderStatus(orderNumber, status);
        return ResponseEntity.ok(response);
    }

    /**
     * Get an order by its order number.
     *
     * @param orderNumber the order number
     * @return the order
     */
    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable final String orderNumber) {
        final OrderResponse response = orderService.getOrder(orderNumber);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all orders.
     *
     * @return list of all orders
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        final List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Get all orders for a specific customer.
     *
     * @param customerId the customer ID
     * @return list of orders for the customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable final Long customerId) {
        final List<OrderResponse> orders = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
}
