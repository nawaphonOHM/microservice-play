package nawaphon.microservices.order_service.services;

import nawaphon.microservice.main.common.pojo.Customer;
import nawaphon.microservice.main.common.pojo.Order;
import nawaphon.microservice.shared_database.common.exceptions.CustomerNotFoundException;
import nawaphon.microservice.shared_database.common.repositories.CustomerRepository;
import nawaphon.microservice.shared_database.common.repositories.OrderRepository;
import nawaphon.microservices.order_service.exceptions.InsufficientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class RequiredTransactionalService {
    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    public RequiredTransactionalService(final OrderRepository orderRepository, final CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public boolean addOrder(Order order) {
        final Customer customer = this.customerRepository.findById(order.getCustomerId().getId()).orElseThrow(CustomerNotFoundException::new);

        customer.setCreditLimit(customer.getCreditLimit().subtract(new BigDecimal(String.valueOf(order.getTotal()))));

        if (customer.getCreditLimit().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientException();
        }

        orderRepository.save(order);

        return true;

    }
}
