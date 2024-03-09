package nawaphon.microservices.event_sourcing.consumer.controllers;

import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.CustomerDetail;
import nawaphon.microservices.event_sourcing.consumer.pojo.Message;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final RestTemplate restTemplate;
    private final String friendIp;

    public MainController(final RestTemplate restTemplate,
                          @Value("${connection.friend-service}") final String friendIp) {
        this.restTemplate = restTemplate;

        this.friendIp = friendIp;
    }

    @KafkaListener(topics = "Greeting", groupId = "group00")
    public void sendEvent(@RequestBody final Message message) {
        logger.info("I've received message: {}", message.getMessage());

    }

    @GetMapping("/get-customer/{uuid}")
    public ResponseMessage<Customer> getCustomer(@PathVariable final UUID uuid) {
        final Customer result = this.restTemplate.getForEntity(friendIp + "/get-customer/" + uuid, Customer.class)
                .getBody();

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
    }

    @GetMapping("/get-customer-details/{uuid}")
    public ResponseMessage<CustomerDetail> getCustomerDetail(@PathVariable final UUID uuid) {
        final CustomerDetail result = this.restTemplate.getForEntity(friendIp + "/get-customer-details/" + uuid,
                CustomerDetail.class).getBody();

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
    }
}
