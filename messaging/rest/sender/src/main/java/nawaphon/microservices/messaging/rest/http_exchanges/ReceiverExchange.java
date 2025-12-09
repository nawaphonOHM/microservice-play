package nawaphon.microservices.messaging.rest.http_exchanges;


import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("${connection.friend-service}")
public interface ReceiverExchange {
}
