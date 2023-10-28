package nawaphon.microservice.shared_database.common.pojo;

import nawaphon.microservice.main.common.pojo.Order;
import nawaphon.microservice.shared_database.common.enums.OrderStatus;

public class OrderStatusEnvelop {

    private final Order order;
    private final OrderStatus status;


    public OrderStatusEnvelop(final Order order, final OrderStatus status) {
        this.order = order;
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
