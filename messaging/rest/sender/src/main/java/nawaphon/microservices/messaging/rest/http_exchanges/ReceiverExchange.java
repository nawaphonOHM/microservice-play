package nawaphon.microservices.messaging.rest.http_exchanges;


import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface ReceiverExchange {

    @GetExchange(value = "/get-customer/{uuid}", version = "1.0")
    Customer getCustomer(@PathVariable UUID uuid);

    @GetExchange(value = "/get-customer-details/{uuid}", version = "1.0")
    CustomerDetail getCustomerDetail(@PathVariable UUID uuid);
}
