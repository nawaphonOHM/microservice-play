package nawaphon.microservices.event_sourcing.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "nawaphon.microservices.event_sourcing.consumer.configurations")
public class EventSourcingServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingServiceConsumerApplication.class, args);
    }

}
