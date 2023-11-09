package nawaphon.microservices.order_service.pojo;


import nawaphon.microservices.order_service.enums.OrderStatus;

import java.util.UUID;

public class OrderStatusEnvelop {

    private final UUID orderId;

    private final UUID customerID;
    private final OrderStatus status;


    public OrderStatusEnvelop(UUID orderId, UUID customerID, OrderStatus status) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.status = status;
    }

    public OrderStatusEnvelop(UUID customerID, OrderStatus status) {
        this.orderId = null;
        this.customerID = customerID;
        this.status = status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
