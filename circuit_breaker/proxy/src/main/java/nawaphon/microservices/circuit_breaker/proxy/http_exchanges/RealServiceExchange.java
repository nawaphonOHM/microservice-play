package nawaphon.microservices.circuit_breaker.proxy.http_exchanges;


import nawaphon.microservices.circuit_breaker.proxy.pojo.Message;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("${service-ip}")
public interface RealServiceExchange {

    Message getCustomer();
}
