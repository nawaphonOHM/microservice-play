package nawaphon.microservices.order_service.test_configurations;

import nawaphon.microservices.order_service.services.MainService;
import nawaphon.microservices.order_service.services.NonRequiredTransactionalService;
import nawaphon.microservices.order_service.services.RequiredTransactionalService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService(final RequiredTransactionalService requiredTransactionalService, final NonRequiredTransactionalService nonRequiredTransactionalService) {

        return new MainService(requiredTransactionalService, nonRequiredTransactionalService);
    }
}
