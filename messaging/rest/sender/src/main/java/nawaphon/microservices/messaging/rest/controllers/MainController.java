package nawaphon.microservices.messaging.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import nawaphon.microservices.messaging.rest.pojo.ResponseMessage;
import nawaphon.microservices.messaging.rest.utils.CustomerDetailsParameterizedTypeReference;
import nawaphon.microservices.messaging.rest.utils.CustomerParameterizedTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) {
        final String url = friendIp + "/get-customer/" + uuid;
        final Customer result = this.restTemplate.exchange(url,
                        HttpMethod.GET, null, new CustomerParameterizedTypeReference())
                .getBody();

        try {
            logger.debug("Call {}: response: {}", url, objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            logger.error("Unable write log");
        }

        assert result != null;
        return result;
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) {
        return this.restTemplate.exchange(friendIp + "/get-customer-details/" + uuid,
                HttpMethod.GET, null,
                new CustomerDetailsParameterizedTypeReference()).getBody();
    }
}
