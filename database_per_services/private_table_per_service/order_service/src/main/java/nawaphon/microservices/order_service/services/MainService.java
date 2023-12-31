package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.controllers.pojo.Order;
import nawaphon.microservices.order_service.controllers.pojo.ResponseMessage;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final OrderRepository orderRepository;

    public MainService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public ResponseMessage<List<Order>> firstService() {
        final List<Order> results = this.orderRepository.findAll();

        return new ResponseMessage<>(200, "Done", results);
    }
}
