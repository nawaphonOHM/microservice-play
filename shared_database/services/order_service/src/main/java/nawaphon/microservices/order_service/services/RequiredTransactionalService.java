package nawaphon.microservices.order_service.services;

import nawaphon.microservice.main.common.pojo.Order;
import nawaphon.microservice.shared_database.common.enums.OrderStatus;
import nawaphon.microservice.shared_database.common.pojo.OrderStatusEnvelop;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequiredTransactionalService {
    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final OrderRepository orderRepository;

    public RequiredTransactionalService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderStatusEnvelop addOrder(Order order) {
        return new OrderStatusEnvelop(orderRepository.save(order), OrderStatus.PENDING);
    }
}
