package nawaphon.microservices.transactional_outbox_pattern.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Order Service with Transactional Outbox Pattern.
 * 
 * The Transactional Outbox Pattern ensures reliable message publishing by:
 * 1. Storing outbox messages in the same transaction as the business entity
 * 2. Using a separate process to publish these messages to the message broker
 */
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
