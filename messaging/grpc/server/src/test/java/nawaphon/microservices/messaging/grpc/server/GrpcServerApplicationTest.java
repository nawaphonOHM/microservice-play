package nawaphon.microservices.messaging.grpc.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {"spring.profiles.active=test"})
class GrpcServerApplicationTest {

    private final ApplicationContext context;

    GrpcServerApplicationTest(ApplicationContext context) {
        this.context = context;
    }


    @Test
    void contextLoads() {
        assertNotNull(context);
    }

}