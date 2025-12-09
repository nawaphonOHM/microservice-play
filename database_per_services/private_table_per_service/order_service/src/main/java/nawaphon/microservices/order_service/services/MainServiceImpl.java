package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    private final OrderRepository orderRepository;

    public MainServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> firstService() {
        return this.orderRepository.findAll();
    }
}
