package nawaphon.microservices.server_side_discovery.service_a;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {"spring.profiles.active=test"})
class ServiceAApplicationTest {

    private final ApplicationContext context;

    ServiceAApplicationTest(ApplicationContext context) {
        this.context = context;
    }

    @Test
    void contextLoads() {
    }

}