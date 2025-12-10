package nawaphon.microservices.circuit_breaker.proxy.http_exchanges;


import nawaphon.microservices.circuit_breaker.proxy.pojo.Message;
import org.springframework.web.service.annotation.GetExchange;

public interface RealServiceExchange {

    @GetExchange("/first-get")
    Message getCustomer();
}
