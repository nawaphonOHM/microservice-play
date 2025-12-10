package nawaphon.microservices.messaging.rest.configurations;

import nawaphon.microservices.messaging.rest.http_exchanges.ReceiverExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.net.URI;


@Configuration
class ReceiverExchangeConfiguration {

    @Bean
    public ReceiverExchange receiverExchange(RestClient.Builder builder, ConnectionConfiguration connectionConfiguration) {
        final var restClient = builder.baseUrl(URI.create(connectionConfiguration.getFriendService())).build();

        return HttpServiceProxyFactory
                .builderFor(
                        RestClientAdapter
                                .create(restClient)
                )
                .build()
                .createClient(ReceiverExchange.class);
    }

}
