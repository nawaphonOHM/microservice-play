package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

/**
 * Enum representing the possible states of an order.
 */
public enum OrderStatus {
    /**
     * Order has been created but not yet processed
     */
    CREATED,
    
    /**
     * Order has been paid for
     */
    PAID,
    
    /**
     * Order is being processed
     */
    PROCESSING,
    
    /**
     * Order has been shipped
     */
    SHIPPED,
    
    /**
     * Order has been delivered to the customer
     */
    DELIVERED,
    
    /**
     * Order has been cancelled
     */
    CANCELLED,
    
    /**
     * Order has been returned
     */
    RETURNED
}