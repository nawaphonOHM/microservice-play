package nawaphon.microservices.circuit_breaker.proxy.http_exchanges;


import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("${service-ip}")
public interface RealServiceExchange {
}
