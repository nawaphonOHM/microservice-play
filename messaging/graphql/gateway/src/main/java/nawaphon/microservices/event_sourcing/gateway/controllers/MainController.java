package nawaphon.microservices.event_sourcing.gateway.controllers;

import nawaphon.microservices.event_sourcing.gateway.components.FakeDatabaseComponent;
import nawaphon.microservices.event_sourcing.gateway.pojo.Customer;
import nawaphon.microservices.event_sourcing.gateway.pojo.CustomerDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final FakeDatabaseComponent fakeDatabaseComponent;

    public MainController(final FakeDatabaseComponent fakeDatabaseComponent) {
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }

    @QueryMapping("findCustomerById")
    public Customer getCustomer(@Argument final UUID id) {
        return fakeDatabaseComponent.getCustomers().stream().filter((var1) -> var1.getId().compareTo(id) == 0)
                .findFirst().orElse(new Customer());
    }

    @SchemaMapping("details")
    public CustomerDetail getCustomerDetails(final Customer customer) {
        return fakeDatabaseComponent.getCustomerDetails().stream()
                .filter((var1) -> var1.getCustomerId().compareTo(customer.getDetailsId()) == 0)
                .findFirst().orElse(new CustomerDetail());
    }
}
