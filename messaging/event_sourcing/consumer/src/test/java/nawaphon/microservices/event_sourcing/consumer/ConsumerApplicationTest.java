package nawaphon.microservices.event_sourcing.consumer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class ConsumerApplicationTest {

    private final ApplicationContext context;

    ConsumerApplicationTest(ApplicationContext context) {
        this.context = context;
    }

    void contextLoads() {}
}