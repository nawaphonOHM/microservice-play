package nawaphon.microservices.event_sourcing.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class EventSourcingServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingServiceCustomerApplication.class, args);
    }

}
