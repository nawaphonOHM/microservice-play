package nawaphon.microservices.event_sourcing.producer.controllers;

import nawaphon.microservices.event_sourcing.producer.components.FakeDatabaseComponent;
import nawaphon.microservices.event_sourcing.producer.pojo.Customer;
import nawaphon.microservices.event_sourcing.producer.pojo.Message;
import nawaphon.microservices.event_sourcing.producer.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final KafkaTemplate<UUID, Message> kafkaTemplate;

    private final FakeDatabaseComponent fakeDatabaseComponent;


    public MainController(final KafkaTemplate<UUID, Message> kafkaTemplate,
                          final FakeDatabaseComponent fakeDatabaseComponent) {
        this.kafkaTemplate = kafkaTemplate;
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }


    @PostMapping("/send-message")
    public ResponseMessage<String> sendEvent(@RequestBody final Message message) {

        kafkaTemplate.send("Greeting", UUID.randomUUID(), message);


        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), "Done");
    }

    @GetMapping("/get-customer/{uuid}")
    public ResponseMessage<Customer> getCustomer(@PathVariable final UUID uuid) {
        final Customer result = fakeDatabaseComponent.getCustomers().stream().filter(
                (predicate) -> predicate.getId() == uuid).findFirst().orElse(null);

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
    }
}
