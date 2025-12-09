package nawaphon.microservices.order_service.repositories;

import nawaphon.microservices.order_service.pojo.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

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
        final var order1 = new Order();
        order1.setStatus(true);
        order1.setTotal(new BigDecimal(10));

        entityManager.persist(order1);

        final var order2 = new Order();
        order2.setStatus(true);
        order2.setTotal(new BigDecimal(20));

        entityManager.persist(order2);

        final var order3 = new Order();
        order3.setStatus(true);
        order3.setTotal(new BigDecimal(50));

        entityManager.persist(order3);
    }

    @Test
    void notNullTest() {

        final var testResult = orderRepository.findAll();

        Assertions.assertNotNull(testResult);
    }

    @Test
    void haveExpectedSizeTest() {

        final var testResult = orderRepository.findAll();
        Assertions.assertEquals(3, testResult.size());
    }

    @Test
    void entityIdShouldHaveBeenGeneratedTest() {
        final var customerTest = orderRepository.findAll().get(0);

        Assertions.assertNotNull(customerTest.getId());
    }
}