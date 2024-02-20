package nawaphon.microservices.event_sourcing.consumer.controllers;

import nawaphon.microservices.event_sourcing.consumer.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @KafkaListener(topics = "Greeting")
    public void sendEvent(@RequestBody final Message message) {
        logger.info("I've received message: {}", message.getMessage());

    }
}
