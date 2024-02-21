package nawaphon.microservices.event_sourcing.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSourcingServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingServiceOrderApplication.class, args);
    }

}
