package nawaphon.microservices.messaging.rest.configurations;

import nawaphon.microservices.messaging.rest.http_exchanges.ReceiverExchange;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;


@Configuration
@ImportHttpServices(ReceiverExchange.class)
class ReceiverExchangeConfiguration {

}
