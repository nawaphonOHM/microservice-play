package nawaphon.microservices.event_sourcing.consumer.controllers;

import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.CustomerId;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import nawaphon.microservices.event_sourcing.consumer.services.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/post-customer")
    public Mono<ResponseMessage<Customer>> postNewCustomer(@RequestBody final Customer newCustomer) {
        return mainService.addCustomer(newCustomer);
    }

    @GetMapping("/get-customer-by-criteria/{id}")
    public ResponseMessage<?> getCustomerByCriteria(@PathVariable("id") final UUID id) {
        return mainService.searchCustomerById(new CustomerId(id));
    }

    @GetMapping("/get-customer-by-criteria/")
    public ResponseMessage<?> getCustomerByCriteria() {
        return mainService.searchCustomers();
    }

    @PatchMapping("/update-customer-credit/{customer-uuid}")
    public ResponseMessage<?> patchCustomerCredit(@PathVariable("customer-uuid") final UUID customerUUID, @RequestBody final CustomerId credit) {
        throw new RuntimeException("Not Implemented");
    }

    @DeleteMapping("delete-customer-credit/{customer-uuid}")
    public ResponseMessage<?> removeCustomer(@PathVariable("customer-uuid") final UUID customerUUID) {
        throw new RuntimeException("Not Implemented");
    }
}
