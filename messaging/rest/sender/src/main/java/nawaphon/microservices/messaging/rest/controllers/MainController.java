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


    private final ObjectMapper objectMapper;


    private final ReceiverExchange receiverExchange;

    public MainController(ObjectMapper objectMapper, ReceiverExchange receiverExchange) {
        this.objectMapper = objectMapper;
        this.receiverExchange = receiverExchange;
    }

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) {
        final var result = this.receiverExchange.getCustomer(uuid);

        assert result != null;
        return result;
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) {
        return this.receiverExchange.getCustomerDetail(uuid);
    }
}
