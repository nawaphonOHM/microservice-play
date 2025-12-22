package nawaphon.microservices.messaging.grpc.client;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {"spring.profiles.active=test"})
class GrpcClientApplicationTest {

    private final ApplicationContext context;

    GrpcClientApplicationTest(ApplicationContext context) {
        this.context = context;
    }
}