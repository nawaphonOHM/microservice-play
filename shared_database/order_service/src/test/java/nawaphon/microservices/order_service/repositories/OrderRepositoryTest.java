package nawaphon.microservices.order_service.repositories;

import nawaphon.microservices.order_service.pojo.Customer;
import nawaphon.microservices.order_service.pojo.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;


    @BeforeEach
    public void setUp() {
        final var customer = new Customer();
        final var order = new Order();

        customer.setCreditLimit(BigDecimal.valueOf(1000));
        order.setTotal(BigDecimal.valueOf(1000));

        order.setCustomerId(customer);

        entityManager.persist(customer);
        entityManager.persist(order);

    }


    @Test
    public void testFindBy() {

        final var order = orderRepository.findByTotal(BigDecimal.valueOf(1000));

        Assertions.assertNotNull(order);

    }

    @Test
    public void testFindAll() {
        final var orders = orderRepository.findAll();

        Assertions.assertEquals(1, orders.size());
    }

}