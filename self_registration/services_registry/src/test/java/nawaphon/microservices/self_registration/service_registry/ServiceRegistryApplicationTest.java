package nawaphon.microservices.self_registration.service_registry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class ServiceRegistryApplicationTest {

    private final ApplicationContext context;

    ServiceRegistryApplicationTest(ApplicationContext context) {
        this.context = context;
    }

    @Test
    void contextLoads() {
    }
}