package nawaphon.microservices.messaging.rest.receiver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.messaging.rest.receiver.pojo.Message;
import nawaphon.microservices.messaging.rest.receiver.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/")
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final KafkaTemplate<UUID, Message> kafkaTemplate;

    private final ObjectMapper objectMapper;


    public MainController(final KafkaTemplate<UUID, Message> kafkaTemplate, final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @PostMapping("/send-message")
    public ResponseMessage<String> sendEvent(@RequestBody final Message message) throws JsonProcessingException {

        logger.debug("message to be sent: {}", objectMapper.writeValueAsString(message));
        kafkaTemplate.send("Greeting", UUID.randomUUID(), message);


        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), "Done");
    }
}
