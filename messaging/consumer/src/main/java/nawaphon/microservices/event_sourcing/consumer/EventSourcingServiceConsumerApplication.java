package nawaphon.microservices.event_sourcing.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSourcingServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingServiceConsumerApplication.class, args);
    }

}
