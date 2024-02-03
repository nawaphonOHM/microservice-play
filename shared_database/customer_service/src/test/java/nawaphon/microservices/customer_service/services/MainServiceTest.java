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

import java.util.HashMap;
import java.util.Map;

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
        final Customer customer = new Customer();

        mainService.addNewCustomer(customer);

        BDDMockito.verify(customerRepository, BDDMockito.times(1)).save(BDDMockito.any());
    }

}