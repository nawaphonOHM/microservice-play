package nawaphon.microservices.order_service.test_configurations;

import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.services.MainService;
import nawaphon.microservices.order_service.services.NonRequiredTransactionalService;
import nawaphon.microservices.order_service.services.RequiredTransactionalService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService(final RequiredTransactionalService requiredTransactionalService, final NonRequiredTransactionalService nonRequiredTransactionalService) {

        return new MainService(requiredTransactionalService, nonRequiredTransactionalService);
    }

    @Bean
    public RequiredTransactionalService requiredTransactionalService(final OrderRepository orderRepository) {
        return Mockito.mock(RequiredTransactionalService.class);
    }


    @Bean
    public NonRequiredTransactionalService nonRequiredTransactionalService() {
        return Mockito.mock(NonRequiredTransactionalService.class);
    }


    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }
}
