package nawaphon.microservices.customer_service.test_configurations;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import nawaphon.microservices.customer_service.services.MainService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService(final CustomerRepository customerRepository) {

        return new MainService(customerRepository);
    }

    @Bean
    public CustomerRepository customerRepository() {
        final CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

        final Customer testOrder = Mockito.mock(Customer.class);


        final List<Customer> mockOrders = List.of(testOrder);

        Mockito.when(customerRepository.findAll()).thenReturn(mockOrders);

        return customerRepository;
    }
}
