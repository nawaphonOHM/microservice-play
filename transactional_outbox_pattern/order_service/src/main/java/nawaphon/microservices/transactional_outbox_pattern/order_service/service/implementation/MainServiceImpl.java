package nawaphon.microservices.transactional_outbox_pattern.order_service.service.implementation;

import jakarta.transaction.Transactional;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.Order;
import nawaphon.microservices.transactional_outbox_pattern.order_service.model.OrderOutbox;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderOutboxRepository;
import nawaphon.microservices.transactional_outbox_pattern.order_service.repository.OrderRepository;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import nawaphon.microservices.transactional_outbox_pattern.order_service.utils.ToHashMapModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;

    public MainServiceImpl(final OrderRepository orderRepository, OrderOutboxRepository orderOutboxRepository) {
        this.orderRepository = orderRepository;
        this.orderOutboxRepository = orderOutboxRepository;
    }

    @Override
    @Transactional
    public boolean saveOrder(@NonNull OrderRequest orderRequest) {
        final Order order = new Order();


        order.setOrderName(orderRequest.orderName());
        order.setPrice(orderRequest.price());

        orderRepository.save(order);

        final OrderOutbox orderOutbox = new OrderOutbox();

        orderOutbox.setAggregatetype("NEW_ORDER");
        orderOutbox.setAggregatetype("ORDER_SERVICE");

        orderOutbox.setPayload(ToHashMapModel.convert(order));

        orderOutboxRepository.save(orderOutbox);
        return true;
    }
}
