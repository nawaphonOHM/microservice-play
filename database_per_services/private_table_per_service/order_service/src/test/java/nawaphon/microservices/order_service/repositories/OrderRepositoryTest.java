package nawaphon.microservices.order_service.repositories;

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
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        final Order order1 = new Order();
        order1.setStatus(true);
        order1.setTotal(new BigDecimal(10));

        entityManager.persist(order1);

        final Order order2 = new Order();
        order2.setStatus(true);
        order2.setTotal(new BigDecimal(20));

        entityManager.persist(order2);

        final Order order3 = new Order();
        order3.setStatus(true);
        order3.setTotal(new BigDecimal(50));

        entityManager.persist(order3);
    }

    @Test
    void notNullTest() {

        final List<Order> testResult = orderRepository.findAll();

        Assertions.assertNotNull(testResult);
    }

    @Test
    void haveExpectedSizeTest() {

        final List<Order> testResult = orderRepository.findAll();
        Assertions.assertEquals(3, testResult.size());
    }

    @Test
    void entityIdShouldHaveBeenGeneratedTest() {
        final Order customerTest = orderRepository.findAll().get(0);

        Assertions.assertNotNull(customerTest.getId());
    }
}