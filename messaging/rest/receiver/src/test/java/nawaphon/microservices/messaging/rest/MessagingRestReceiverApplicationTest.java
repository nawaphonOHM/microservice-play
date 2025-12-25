package nawaphon.microservices.messaging.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {"spring.profiles.active=test"})
class MessagingRestReceiverApplicationTest {

    private final ApplicationContext context;

    MessagingRestReceiverApplicationTest(ApplicationContext context) {
        this.context = context;
    }

    @Test
    void contextLoads() {}
}