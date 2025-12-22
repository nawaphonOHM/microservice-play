package nawaphon.microservices.circuit_breaker.real_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class RealServiceApplicationTest {


    private final ApplicationContext context;

    RealServiceApplicationTest(ApplicationContext context) {
        this.context = context;
    }


    @Test
    void contextLoads() {

        assertNotNull(context);
    }

}