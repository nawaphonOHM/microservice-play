package nawaphon.microservices.messaging.grpc.client.controllers;


import nawaphon.microservices.messaging.grpc.client.pojo.Customer;
import nawaphon.microservices.messaging.grpc.client.pojo.CustomerDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @GetMapping("/get-customer/{uuid}")
    public Customer getCustomer(@PathVariable final UUID uuid) {

        return null;
    }

    @GetMapping("/get-customer-details/{uuid}")
    public CustomerDetail getCustomerDetail(@PathVariable final UUID uuid) {
        return null;
    }
}
