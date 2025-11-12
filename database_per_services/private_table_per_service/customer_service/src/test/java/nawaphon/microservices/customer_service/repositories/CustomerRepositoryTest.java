package nawaphon.microservices.customer_service.repositories;

import nawaphon.microservices.customer_service.pojo.Customer;
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
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        final var customer1 = new Customer();
        customer1.setCreditLimit(new BigDecimal(20));

        entityManager.persist(customer1);

        final var customer2 = new Customer();
        customer2.setCreditLimit(new BigDecimal(50));

        entityManager.persist(customer2);

        final var customer3 = new Customer();
        customer3.setCreditLimit(new BigDecimal(100));

        entityManager.persist(customer3);
    }

    @Test
    void notNullTest() {

        final var testResult = customerRepository.findAll();

        Assertions.assertNotNull(testResult);
    }

    @Test
    void haveExpectedSizeTest() {

        final var testResult = customerRepository.findAll();
        Assertions.assertEquals(3, testResult.size());
    }

    @Test
    void entityIdShouldHaveBeenGeneratedTest() {
        final var customerTest = customerRepository.findAll().get(0);

        Assertions.assertNotNull(customerTest.getId());
    }

}