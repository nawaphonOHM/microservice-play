package nawaphon.microservices.transactional_outbox_pattern.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"nawaphon.microservices.transactional_outbox_pattern", "nawaphon.microservices.reusable.model"})
public class OrderServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
