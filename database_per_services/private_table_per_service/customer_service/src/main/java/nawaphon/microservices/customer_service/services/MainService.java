package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Order;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final OrderRepository orderRepository;

    public MainService(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }



    public ResponseMessage<Order> firstService() {
        final List<Order> results = this.orderRepository.findAll();

        return new ResponseMessage<>(200, "Done", results);
    }
}
