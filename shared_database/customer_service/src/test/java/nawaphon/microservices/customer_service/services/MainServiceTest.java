package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import nawaphon.microservices.customer_service.test_configurations.MainServiceMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainServiceMock.class)
public class MainServiceTest {

    @Autowired
    private MainService mainService;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void testAbleToGetCustomerByCriteria() {
        final Map<String, String> params = new HashMap<>();

        mainService.getCustomerByCriteria(params);

        BDDMockito.verify(customerRepository, BDDMockito.times(1)).findBy(
                BDDMockito.any(),
                BDDMockito.any()
        );
    }

    @Test
    void testAbleToAddNewCustomer() {
        final UUID id = UUID.randomUUID();

        final Customer customer = new Customer();

        customer.setId(id);

        mainService.addNewCustomer(customer);

        BDDMockito.verify(customerRepository, BDDMockito.times(1))
                .save(BDDMockito.argThat((var1) -> var1.getId() == id));
    }


    @Test
    void testAbleToRemoveCustomer() {
        final UUID id = UUID.randomUUID();

        mainService.removeCustomer(id);

        BDDMockito.verify(customerRepository, BDDMockito.times(1)).deleteById(BDDMockito.any());
    }

    @Test
    void testAbleToUpdateCredit() {
        final UUID id = UUID.randomUUID();
        final BigDecimal newCredit = BigDecimal.TEN;

        final Customer mockCustomer = BDDMockito.mock(Customer.class);

        final Optional<Customer> optionalCustomer = Optional.of(mockCustomer);

        BDDMockito.given(mockCustomer.getId()).willReturn(id);

        BDDMockito.given(customerRepository.findById(BDDMockito.any())).willReturn(optionalCustomer);

        mainService.updateUserCredit(id, newCredit);

        BDDMockito.verify(customerRepository, BDDMockito.times(1)).findById(
                BDDMockito.any());

        BDDMockito.verify(customerRepository, BDDMockito.times(1))
                .save(BDDMockito.argThat((var1) -> var1.getId() == id));
    }

}