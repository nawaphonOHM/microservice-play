package nawaphon.microservices.circuit_breaker.proxy.configurations;

import nawaphon.microservices.circuit_breaker.proxy.http_exchanges.RealServiceExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
class RealServiceExchangeConfiguration {


    @Bean
    public RealServiceExchange realServiceExchange(RestClient.Builder builder, @Value("${service-ip}") String friendIp) {
        return null;
    }
}
