package nawaphon.microservices.order_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class OrderServiceApplicationTest {

    private final ApplicationContext context;

    OrderServiceApplicationTest(ApplicationContext context) {
        this.context = context;
    }

    void contextLoads() {}
}