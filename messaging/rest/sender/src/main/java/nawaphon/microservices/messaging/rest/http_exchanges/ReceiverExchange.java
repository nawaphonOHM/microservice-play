package nawaphon.microservices.messaging.rest.http_exchanges;


import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface ReceiverExchange {

    @GetExchange("/get-customer/{uuid}")
    Customer getCustomer(@PathVariable UUID uuid);

    @GetExchange("/get-customer-details/{uuid}")
    CustomerDetail getCustomerDetail(@PathVariable UUID uuid);
}
