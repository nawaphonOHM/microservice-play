package nawaphon.microservices.messaging.rest.http_exchanges;


import nawaphon.microservices.reusable.pojo.Customer;
import nawaphon.microservices.reusable.pojo.CustomerDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface ReceiverExchange {

    @GetExchange("/customer/{uuid}/basic")
    Customer getCustomer(@PathVariable UUID uuid);

    @GetExchange("/customer/{uuid}/detail")
    CustomerDetail getCustomerDetail(@PathVariable UUID uuid);
}
