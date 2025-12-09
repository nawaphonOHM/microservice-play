package nawaphon.microservices.messaging.rest.controllers;

import nawaphon.microservices.messaging.rest.http_exchanges.ReceiverExchange;
import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import nawaphon.microservices.messaging.rest.utils.CustomerDetailsParameterizedTypeReference;
import nawaphon.microservices.messaging.rest.utils.CustomerParameterizedTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final ObjectMapper objectMapper;

    private final ReceiverExchange receiverExchange;

    public MainController(final ObjectMapper objectMapper, ReceiverExchange receiverExchange) {
        this.objectMapper = objectMapper;
        this.receiverExchange = receiverExchange;
    }

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) {
        final var url = friendIp + "/get-customer/" + uuid;
        final var result = this.receiverExchange.getCustomer(uuid);

        try {
            logger.debug("Call {}: response: {}", url, objectMapper.writeValueAsString(result));
        } catch (JacksonException e) {
            logger.error("Unable write log");
        }

        assert result != null;
        return result;
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) {
        return this.receiverExchange.getCustomerDetail(uuid);
    }
}
