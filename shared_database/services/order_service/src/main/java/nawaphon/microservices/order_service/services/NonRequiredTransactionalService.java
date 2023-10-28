package nawaphon.microservices.order_service.services;

import nawaphon.microservice.main.common.pojo.Customer;
import nawaphon.microservice.main.common.pojo.Order;
import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservice.shared_database.common.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NonRequiredTransactionalService {
    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final OrderRepository orderRepository;

    public NonRequiredTransactionalService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseMessage<List<Order>> getOrderByCriteria(final Map<String, String> params) {
        final Order probe = new Order();

        probe.setCustomerId(new Customer());

        params.forEach((key, value) -> {
            if (key.equalsIgnoreCase("id")) {
                logger.debug("There is id = {}", value);
                probe.setId(UUID.fromString(value));
            } else if (key.equalsIgnoreCase("customerId")) {
                logger.debug("There is customerId = {}", value);
                probe.getCustomerId().setId(UUID.fromString(value));
            } else if (key.equalsIgnoreCase("status")) {
                logger.debug("There is status = {}", value);
                probe.setStatus(Boolean.parseBoolean(value));
            } else if (key.equalsIgnoreCase("total")) {
                logger.debug("There is total = {}", value);
                probe.setTotal(new BigDecimal(value));
            }
        });

        return orderRepository.findBy(Example.of(probe), (query) -> new ResponseMessage<>(200, "Done", query.all()));
    }
}
