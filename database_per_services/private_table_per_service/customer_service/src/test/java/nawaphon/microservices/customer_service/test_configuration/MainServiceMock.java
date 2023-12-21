package nawaphon.microservices.customer_service.test_configuration;

import nawaphon.microservices.customer_service.services.MainService;
import nawaphon.microservices.customer_service.services.MainServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService() {
        return new MainServiceImpl(customerRepository);
    }


}
