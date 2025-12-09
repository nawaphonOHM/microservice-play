package nawaphon.microservices.circuit_breaker.proxy.configurations;

import nawaphon.microservices.circuit_breaker.proxy.http_exchanges.RealServiceExchange;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;


@Configuration
@ImportHttpServices(RealServiceExchange.class)
class RealServiceExchangeConfiguration {

}
