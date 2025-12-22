package nawaphon.microservices.circuit_breaker.real_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class RealServiceApplicationTest {


    @Test
    void contextLoads() {
    }

}