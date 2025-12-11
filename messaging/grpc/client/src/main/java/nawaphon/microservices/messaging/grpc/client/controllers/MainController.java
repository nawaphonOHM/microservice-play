package nawaphon.microservices.messaging.grpc.client.controllers;


import io.grpc.StatusException;
import nawaphon.microservices.messaging.grpc.client.MainServerGrpc;
import nawaphon.microservices.messaging.grpc.client.pojo.Customer;
import nawaphon.microservices.messaging.grpc.client.pojo.CustomerDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    private final MainServerGrpc.MainServerBlockingV2Stub server;

    public MainController(MainServerGrpc.MainServerBlockingV2Stub server) {
        this.server = server;
    }

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) throws StatusException {

        final var customer = server.customer(
                nawaphon.microservices.messaging.grpc.client.UUID.newBuilder().setValue(uuid.toString()
                ).build()
        );

        return new Customer(
                UUID.fromString(customer.getId().getValue()),
                UUID.fromString(customer.getDetailsId().getValue())
        );
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) throws StatusException {

        final var customerDetail = server.customerDetail(
                nawaphon.microservices.messaging.grpc.client.UUID.newBuilder().setValue(uuid.toString()).build()
        );


        return new CustomerDetail(
                UUID.fromString(customerDetail.getCustomerId().getValue()),
                customerDetail.getFirstName(),
                customerDetail.getLastName()
        );
    }
}
