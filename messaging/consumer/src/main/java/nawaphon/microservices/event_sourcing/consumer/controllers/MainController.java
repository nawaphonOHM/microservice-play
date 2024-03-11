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
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

    private final RestTemplate restTemplate;
    private final String friendIp;

    private final ObjectMapper objectMapper;

    public MainController(final RestTemplate restTemplate,
                          @Value("${connection.friend-service}") final String friendIp,
                          final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;

        this.friendIp = friendIp;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "Greeting", groupId = "group00")
    public void sendEvent(@RequestBody final Message message) {
        logger.info("I've received message: {}", message.getMessage());

    }

    @QueryMapping("getCustomer")
    public Customer customer(@Argument final UUID id) {
        return getCustomer(id).getResults();
    }

    @SchemaMapping("CustomerDetail")
    public CustomerDetail customerDetail(final Customer customer) {
        return getCustomerDetail(customer.getDetailsId()).getResults();
    }

    @GetMapping("/get-customer/{uuid}")
    public ResponseMessage<Customer> getCustomer(@PathVariable final UUID uuid) {
        final String url = friendIp + "/get-customer/" + uuid;
        final ResponseMessage<Customer> result = this.restTemplate.exchange(url,
                        HttpMethod.GET, null, new CustomerParameterizedTypeReference())
                .getBody();

        try {
            logger.debug("Call {}: response: {}", url, objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            logger.error("Unable write log");
        }

        assert result != null;
        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result.getResults());
    }

    @GetMapping("/get-customer-details/{uuid}")
    public ResponseMessage<CustomerDetail> getCustomerDetail(@PathVariable final UUID uuid) {
        final ResponseMessage<CustomerDetail> result = this.restTemplate.exchange(friendIp + "/get-customer-details/" + uuid,
                HttpMethod.GET, null,
                new CustomerDetailsParameterizedTypeReference()).getBody();

        assert result != null;
        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result.getResults());
    }
}
