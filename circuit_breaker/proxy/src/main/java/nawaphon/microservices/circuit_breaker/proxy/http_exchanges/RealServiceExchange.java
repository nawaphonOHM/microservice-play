package nawaphon.microservices.circuit_breaker.proxy.http_exchanges;


import nawaphon.microservices.reusable.pojo.Message;
import org.springframework.web.service.annotation.GetExchange;

public interface RealServiceExchange {

    @GetExchange("/basic-message")
    Message getCustomer();
}
