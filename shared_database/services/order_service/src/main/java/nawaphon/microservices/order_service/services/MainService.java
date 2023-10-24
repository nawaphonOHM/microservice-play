package nawaphon.microservices.order_service.services;

import nawaphon.microservice.pojo.Order;
import nawaphon.microservice.pojo.OrderStatus;
import nawaphon.microservice.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final RequiredTransactionalService requiredTransactionalService;

    private final NonRequiredTransactionalService nonRequiredTransactionalService;

    public MainService(final RequiredTransactionalService requiredTransactionalService, final NonRequiredTransactionalService nonRequiredTransactionalService) {
        this.requiredTransactionalService = requiredTransactionalService;
        this.nonRequiredTransactionalService = nonRequiredTransactionalService;
    }


    public ResponseMessage<List<Order>> getOrderByCriteria(final Map<String, String> params) {
        return nonRequiredTransactionalService.getOrderByCriteria(params);
    }


    public ResponseMessage<List<OrderStatus>> addOrders(final List<Order> orders) {
        final List<OrderStatus> results = new ArrayList<>();


        for (final Order order : orders) {
            logger.debug("Data Object {} will be saved", order.toString());
            results.add(requiredTransactionalService.addOrder(order));
        }


        return new ResponseMessage<>(200, "Done", results);
    }
}
