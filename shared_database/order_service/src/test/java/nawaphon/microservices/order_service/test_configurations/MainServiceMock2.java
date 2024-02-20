package nawaphon.microservices.order_service.test_configurations;

import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.services.NonRequiredTransactionalService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MainServiceMock2 {


    @Bean
    public NonRequiredTransactionalService nonRequiredTransactionalService(final OrderRepository orderRepository) {
        return new NonRequiredTransactionalService(orderRepository);
    }


    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }
}
