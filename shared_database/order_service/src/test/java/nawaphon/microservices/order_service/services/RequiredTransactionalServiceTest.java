package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.exceptions.CustomerNotFoundException;
import nawaphon.microservices.order_service.exceptions.InsufficientException;
import nawaphon.microservices.order_service.pojo.Customer;
import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.repositories.CustomerRepository;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.test_configurations.MainServiceMock3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainServiceMock3.class)
public class RequiredTransactionalServiceTest {

    @Autowired
    private CustomerRepository customerRepositoryMock;
    @Autowired
    private OrderRepository orderRepositoryMock;

    @Autowired
    private RequiredTransactionalService requiredTransactionalService;

    @Test
    public void addOrderWithValidCustomerAndCredit_shouldReturnTrue() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCreditLimit(new BigDecimal(1000));

        final Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomerId(customer);
        order.setTotal(new BigDecimal(500));

        Mockito.when(customerRepositoryMock.findById(Mockito.any())).thenReturn(Optional.of(customer));
        Mockito.when(orderRepositoryMock.save(Mockito.any(Order.class))).thenReturn(order);

        boolean result = requiredTransactionalService.addOrder(order);

        assertTrue(result);
        Mockito.verify(orderRepositoryMock, Mockito.times(1)).save(order);
    }


    @Test
    public void addOrderWithInvalidCustomer_shouldThrowCustomerNotFoundException() {
        final Order order = new Order();
        final Customer customer = new Customer();
        order.setId(UUID.randomUUID());
        order.setCustomerId(customer);
        Mockito.when(customerRepositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> requiredTransactionalService.addOrder(order));
    }

    @Test
    public void addOrderWithInsufficientCustomerCredit_shouldThrowInsufficientException() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCreditLimit(new BigDecimal(500));

        final Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomerId(customer);
        order.setTotal(new BigDecimal(1000));

        Mockito.when(customerRepositoryMock.findById(Mockito.any())).thenReturn(Optional.of(customer));

        assertThrows(InsufficientException.class, () -> requiredTransactionalService.addOrder(order));
    }
}