package nawaphon.microservices.order_service.services;

import enums.Status;
import nawaphon.microservice.pojo.Order;
import nawaphon.microservice.pojo.OrderStatus;
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

    public OrderStatus addOrder(Order order) {
        return new OrderStatus(orderRepository.save(order), Status.PASS);
    }
}
