package nawaphon.microservices.customer_service.repositories;

import nawaphon.microservices.customer_service.pojo.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private UUID uuid1;


    @BeforeEach
    public void setUp() {
        final Customer customer = new Customer();

        customer.setCreditLimit(BigDecimal.valueOf(1000));

        uuid1 = entityManager.persist(customer).getId();

    }


    @Test
    void ableFindByProperlyWhenSetOnlyId() {
        final Customer customer = new Customer();

        customer.setId(uuid1);

        final List<Customer> foundCustomer = customerRepository.findBy(Example.of(customer),
                FluentQuery.FetchableFluentQuery::all);

        Assertions.assertEquals(1, foundCustomer.size());
        Assertions.assertEquals(uuid1, foundCustomer.get(0).getId());
        Assertions.assertEquals(BigDecimal.valueOf(1000), foundCustomer.get(0).getCreditLimit());

    }

    @Test
    void ableFindByProperlyWhenSetOnlyCredit() {
        final Customer customer = new Customer();

        customer.setCreditLimit(BigDecimal.valueOf(1000));

        final List<Customer> foundCustomer = customerRepository.findBy(Example.of(customer),
                FluentQuery.FetchableFluentQuery::all);

        Assertions.assertEquals(1, foundCustomer.size());
        Assertions.assertEquals(uuid1, foundCustomer.get(0).getId());
        Assertions.assertEquals(BigDecimal.valueOf(1000), foundCustomer.get(0).getCreditLimit());

    }

}