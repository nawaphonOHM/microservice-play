package nawaphon.microservices.messaging.rest.configurations;

import nawaphon.microservices.messaging.rest.http_exchanges.ReceiverExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
class ReceiverExchangeConfiguration {

    @Bean
    public ReceiverExchange receiverExchange(RestClient.Builder builder, ConnectionConfiguration connectionConfiguration) {
        return null;
    }

}
