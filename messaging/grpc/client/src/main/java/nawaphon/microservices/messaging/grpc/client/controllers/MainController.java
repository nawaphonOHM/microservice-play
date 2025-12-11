package nawaphon.microservices.messaging.grpc.client.controllers;


import io.grpc.StatusException;
import nawaphon.microservices.messaging.grpc.client.MainServerGrpc;
import nawaphon.microservices.messaging.grpc.client.pojo.Customer;
import nawaphon.microservices.messaging.grpc.client.pojo.CustomerDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final MainServerGrpc.MainServerBlockingV2Stub server;

    public MainController(MainServerGrpc.MainServerBlockingV2Stub server) {
        this.server = server;
    }

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) throws StatusException {

        log.info("Get customer by uuid: {}", uuid);

        final var customer = server.customer(
                nawaphon.microservices.messaging.grpc.client.UUID.newBuilder().setValue(uuid.toString()
                ).build()
        );

        log.info("Customer: {}", customer);

        return new Customer(
                UUID.fromString(customer.getId().getValue()),
                UUID.fromString(customer.getDetailsId().getValue())
        );
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) throws StatusException {

        log.info("Get customer detail by uuid: {}", uuid);

        final var customerDetail = server.customerDetail(
                nawaphon.microservices.messaging.grpc.client.UUID.newBuilder().setValue(uuid.toString()).build()
        );

        log.info("Customer detail: {}", customerDetail);


        return new CustomerDetail(
                UUID.fromString(customerDetail.getCustomerId().getValue()),
                customerDetail.getFirstName(),
                customerDetail.getLastName()
        );
    }
}
