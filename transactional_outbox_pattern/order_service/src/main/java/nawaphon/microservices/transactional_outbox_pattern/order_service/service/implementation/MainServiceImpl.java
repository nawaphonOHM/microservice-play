package nawaphon.microservices.transactional_outbox_pattern.order_service.service.implementation;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderRepository;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MainServiceImpl implements MainService {
    private final OrderRepository orderRepository;

    public MainServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean saveOrder(@NonNull OrderRequest orderRequest) {
        final Order order = new Order();


        order.setOrderName(orderRequest.orderName());
        order.setPrice(orderRequest.price());

        orderRepository.save(order);
        return true;
    }
}
