package nawaphon.microservices.circuit_breaker.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealServiceApplication.class, args);
    }

}
