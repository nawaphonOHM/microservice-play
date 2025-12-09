package nawaphon.microservices.messaging.rest.http_exchanges;


import nawaphon.microservices.messaging.rest.pojo.Customer;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("${connection.friend-service}")
public interface ReceiverExchange {

    Customer getCustomer();
}
