package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.enums.OrderStatus;
import nawaphon.microservices.order_service.exceptions.InsufficientException;
import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.pojo.OrderStatusEnvelop;
import nawaphon.microservices.order_service.pojo.ResponseMessage;
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


    public ResponseMessage<List<OrderStatusEnvelop>> addOrders(final List<Order> orders) {
        final List<OrderStatusEnvelop> results = new ArrayList<>();


        for (final Order order : orders) {
            logger.debug("Data Object {}; total {} will be saved", order.getId(), order.getTotal());
            boolean saveSuccess;

            try {
                saveSuccess = requiredTransactionalService.addOrder(order);

                logger.debug("Data Object {}; total {} save status {}", order.getId(), order.getTotal(), saveSuccess);
            } catch (final InsufficientException exception) {
                saveSuccess = false;
                logger.error("Data Object {} will be saved has insufficient credit", order.getId());
            }

            results.add(new OrderStatusEnvelop(order, saveSuccess ? OrderStatus.ACCEPT : OrderStatus.REJECT));
        }


        return new ResponseMessage<>(200, "Done", results);
    }
}
