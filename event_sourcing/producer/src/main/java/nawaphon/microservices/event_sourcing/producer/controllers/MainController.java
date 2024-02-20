package nawaphon.microservices.event_sourcing.producer.controllers;

import nawaphon.microservices.event_sourcing.producer.pojo.Message;
import nawaphon.microservices.event_sourcing.producer.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final KafkaTemplate<UUID, Message> kafkaTemplate;


    public MainController(final KafkaTemplate<UUID, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @PostMapping("/send-message")
    public ResponseMessage<String> sendEvent(@RequestBody final Message message) {

        kafkaTemplate.send("Greeting", UUID.randomUUID(), message);


        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), "Done");
    }
}
