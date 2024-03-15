package nawaphon.microservices.event_sourcing.consumer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.CustomerDetail;
import nawaphon.microservices.event_sourcing.consumer.pojo.Message;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import nawaphon.microservices.event_sourcing.consumer.utils.CustomerDetailsParameterizedTypeReference;
import nawaphon.microservices.event_sourcing.consumer.utils.CustomerParameterizedTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController() {
    }

    @KafkaListener(topics = "Greeting", groupId = "group00")
    public void sendEvent(@RequestBody final Message message) {
        logger.info("I've received message: {}", message.getMessage());

    }
}
