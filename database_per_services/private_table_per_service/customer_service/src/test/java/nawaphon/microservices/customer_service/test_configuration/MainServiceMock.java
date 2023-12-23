package nawaphon.microservices.customer_service.test_configuration;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import nawaphon.microservices.customer_service.services.MainService;
import nawaphon.microservices.customer_service.services.MainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService() {

        final Customer customerTest = Mockito.mock(Customer.class);

        Mockito.when(customerTest.getCreditLimit()).thenReturn(new BigDecimal(1000));

        final List<Customer> customers = List.of(customerTest);

        final CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

        Mockito.when(customerRepository.findAll()).thenReturn(customers);


        return new MainServiceImpl(customerRepository);
    }


}
