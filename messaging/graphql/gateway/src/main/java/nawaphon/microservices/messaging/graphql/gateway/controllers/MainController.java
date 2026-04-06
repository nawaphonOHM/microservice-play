package nawaphon.microservices.messaging.graphql.gateway.controllers;

import nawaphon.microservices.messaging.graphql.gateway.components.FakeDatabaseComponent;
import nawaphon.microservices.reusable.pojo.Customer;
import nawaphon.microservices.reusable.pojo.CustomerDetail;
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
        logger.info("Getting customer with id: {}", id);
        return fakeDatabaseComponent.getCustomers().stream().filter((var1) -> var1.id().compareTo(id) == 0)
                .findFirst().orElse(new Customer(null, null));
    }

    @SchemaMapping("details")
    public CustomerDetail getCustomerDetails(final Customer customer) {
        logger.info("Getting customer details for customer with id: {}", customer.id());
        return fakeDatabaseComponent.getCustomerDetails().stream()
                .filter((var1) -> var1.customerId().compareTo(customer.detailsId()) == 0)
                .findFirst().orElse(new CustomerDetail(null, null, null));
    }
}
