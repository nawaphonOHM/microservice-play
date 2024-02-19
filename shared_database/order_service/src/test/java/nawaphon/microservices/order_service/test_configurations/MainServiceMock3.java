package nawaphon.microservices.order_service.test_configurations;

import nawaphon.microservices.order_service.repositories.CustomerRepository;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.services.RequiredTransactionalService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MainServiceMock3 {


    @Bean
    public RequiredTransactionalService requiredTransactionalService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        return new RequiredTransactionalService(orderRepository, customerRepository);
    }


    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }
}
