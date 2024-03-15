package nawaphon.microservices.messaging.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "nawaphon.microservices.event_sourcing.consumer.configurations")
public class MessagingRestSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingRestSenderApplication.class, args);
    }

}
