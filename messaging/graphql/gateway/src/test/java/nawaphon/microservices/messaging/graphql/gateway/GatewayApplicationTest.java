package nawaphon.microservices.messaging.graphql.gateway;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest(properties = {"spring.profiles.active=test"})
class GatewayApplicationTest {

    private final ApplicationContext context;

    GatewayApplicationTest(ApplicationContext context) {
        this.context = context;
    }
    void contextLoads() {}
}