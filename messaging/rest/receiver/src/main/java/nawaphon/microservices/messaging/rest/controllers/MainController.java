package nawaphon.microservices.messaging.rest.controllers;

import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import nawaphon.microservices.messaging.rest.pojo.ResponseMessage;
import nawaphon.microservices.messaging.rest.components.FakeDatabaseComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/")
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    private final FakeDatabaseComponent fakeDatabaseComponent;


    public MainController(final FakeDatabaseComponent fakeDatabaseComponent) {
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) {
        return fakeDatabaseComponent.getCustomers().stream().filter(
                (predicate) -> predicate.id().compareTo(uuid) == 0).findFirst().orElse(null);
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) {
        return fakeDatabaseComponent.getCustomerDetails().stream().filter(
                (predicate) -> predicate.customerId().compareTo(uuid) == 0).findFirst().orElse(null);
    }
}
