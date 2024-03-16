package nawaphon.microservices.messaging.event_sourcing.producer.controllers;

import nawaphon.microservices.messaging.event_sourcing.producer.pojo.Customer;
import nawaphon.microservices.messaging.event_sourcing.producer.pojo.CustomerDetail;
import nawaphon.microservices.messaging.event_sourcing.producer.pojo.ResponseMessage;
import nawaphon.microservices.messaging.event_sourcing.producer.components.FakeDatabaseComponent;
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
    public ResponseMessage<Customer> getCustomer(@PathVariable final UUID uuid) {
        final Customer result = fakeDatabaseComponent.getCustomers().stream().filter(
                (predicate) -> predicate.getId().compareTo(uuid) == 0).findFirst().orElse(null);

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
    }

    @GetMapping("/get-customer-details/{uuid}")
    public ResponseMessage<CustomerDetail> getCustomerDetail(@PathVariable final UUID uuid) {
        final CustomerDetail result = fakeDatabaseComponent.getCustomerDetails().stream().filter(
                (predicate) -> predicate.getCustomerId().compareTo(uuid) == 0).findFirst().orElse(null);

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
    }
}
